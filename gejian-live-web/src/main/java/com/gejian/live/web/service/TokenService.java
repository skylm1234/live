package com.gejian.live.web.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.gejian.live.dao.entity.TokenEntity;
import com.gejian.live.web.verification.ValidType;
import com.gejian.live.web.verification.VerifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author fengliang
 * @Date 2021/9/22
 * @description:
 */
@Component
@Slf4j
public class TokenService {
	/**
	 * token长度
	 */
	private static final int TOKEN_LENGTH = 10;
	/**
	 * 推流模板key
	 */
	private static final String TOKEN_PUSH = "live:toke:{}:push";
	/**
	 * 拉流模板key
	 */
	private static final String TOKEN_PULL = "live:toke:{}:pull";
	/**
	 * 时间单位 分钟 PUSH
	 */
	private static final int TOKEN_PUSH_TIME = 24 * 60;

	/**
	 * 时间单位 分钟 PULL
	 */
	private static final int TOKEN_PULL_TIME = 10;
	/**
     * md5加密对象
	 */
	private Digester digester=new Digester(DigestAlgorithm.MD5);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;


	/**
	 * 获取明天时间戳
	 *
	 * @return
	 */
	public Long getTomorrowTimeMillis() {

		return DateUtil.tomorrow().getTime();
	}

	/**
	 * 获取10分钟后的时间戳
	 * @return
	 */
	public Long getTenMillis(){

		return System.currentTimeMillis()+TOKEN_PULL_TIME*60*1000;
	}

	/**
	 * 构建token
	 * @param originToken
	 * @param expireTimestamp
	 * @return
	 */
	private TokenEntity buildToken(String originToken, Long expireTimestamp){
		TokenEntity tokenEntity = new TokenEntity();
		String salt = String.valueOf(expireTimestamp).substring(0, String.valueOf(expireTimestamp).length() - 4);
		digester.setSalt(salt.getBytes());
		String token = digester.digestHex(originToken);
		tokenEntity.setToken(token);
		tokenEntity.setExpireTimestamp(expireTimestamp);
		log.info("源字符串[{}]=====时间戳[{}]===加密字符串[{}]====盐值[{}]",originToken,expireTimestamp,tokenEntity.getToken(),salt);
		return tokenEntity;
	}

	/**
	 * 生成随机token
	 *
	 * @param roomId
	 * @param validType
	 * @return
	 */
	public TokenEntity generateTokenSalt(String roomId, ValidType validType) {
		String redisTokenKey = getRedisTokenKey(roomId, validType);
		String originToken = stringRedisTemplate.opsForValue().get(redisTokenKey);
		if(StrUtil.isBlank(originToken)){
			//重新生成
			originToken= RandomUtil.randomString(TOKEN_LENGTH);
			if (validType == ValidType.PULL) {
				//PULL 拉流时间戳
				Long pullTimeMillis = getTenMillis();
				TokenEntity tokenEntity = buildToken(originToken, pullTimeMillis);
				stringRedisTemplate.opsForValue().set(redisTokenKey, originToken, TOKEN_PULL_TIME, TimeUnit.MINUTES);
				return tokenEntity;
			} else if (validType == ValidType.PUSH) {
				//PUSH 推流时间戳
				Long pushTimeMillis = getTomorrowTimeMillis();
				TokenEntity tokenEntity = buildToken(originToken, pushTimeMillis);
				stringRedisTemplate.opsForValue().set(redisTokenKey, originToken, TOKEN_PUSH_TIME, TimeUnit.MINUTES);
				return tokenEntity;
			}
		}else {
			//获取redis的Token原字符串 重新加密字符串
			Long expire = stringRedisTemplate.opsForValue().getOperations().getExpire(redisTokenKey);
			Long limit=System.currentTimeMillis()+expire*1000;
			TokenEntity tokenEntity = buildToken(originToken, limit);
			return tokenEntity;
		}
		return null;
	}

	/**
	 * 刷新Token
	 * @param roomId
	 * @param validType
	 * @return
	 */
	public TokenEntity refresTokenSalt(String roomId, ValidType validType){
		String redisTokenKey = getRedisTokenKey(roomId, validType);
		String originToken= RandomUtil.randomString(TOKEN_LENGTH);
		if (validType == ValidType.PULL) {
			//PULL 拉流时间戳
			Long pullTimeMillis = getTenMillis();
			TokenEntity tokenEntity = buildToken(originToken, pullTimeMillis);
			stringRedisTemplate.opsForValue().set(redisTokenKey, originToken, TOKEN_PULL_TIME, TimeUnit.MINUTES);
			return tokenEntity;
		} else if (validType == ValidType.PUSH) {
			//PUSH 推流时间戳
			Long pushTimeMillis = getTomorrowTimeMillis();
			TokenEntity tokenEntity = buildToken(originToken, pushTimeMillis);
			stringRedisTemplate.opsForValue().set(redisTokenKey, originToken, TOKEN_PUSH_TIME, TimeUnit.MINUTES);
			return tokenEntity;
		}
		return null;
	}



	/**
	 * token 校验时间戳和token
	 * @param roomId
	 * @param validType
	 * @param tokenEntity
	 * @return
	 */
	public Boolean judgeTokenAndTimeStamp(String roomId, ValidType validType, TokenEntity tokenEntity) {
		if (StrUtil.isBlank(tokenEntity.getToken()) || null == tokenEntity.getExpireTimestamp()) {
			return Boolean.FALSE;
		}
		String redisTokenKey = getRedisTokenKey(roomId, validType);
		if (!StrUtil.isBlank(redisTokenKey)) {
			Long expire = stringRedisTemplate.opsForValue().getOperations().getExpire(redisTokenKey);
			if (expire == -2) {
				return Boolean.FALSE;
			}
			//token过期时间校验
			boolean lessOrEqual = false;
			long current = DateUtil.current();
			long timeLimit=current+expire*1000;
			if(Math.abs(timeLimit-tokenEntity.getExpireTimestamp())<=10000){
				lessOrEqual=true;
			}

			if (lessOrEqual) {
				//token加密是否正常校验
				String origin = stringRedisTemplate.opsForValue().get(redisTokenKey);
				if (!StrUtil.isBlank(origin)) {
					TokenEntity redisTokenEntity = buildToken(origin, tokenEntity.getExpireTimestamp());
					//token校验
					if (Objects.equals(tokenEntity.getToken(),redisTokenEntity.getToken())) {
						return Boolean.TRUE;
					}
				}
			}
		}
		return Boolean.FALSE;
	}


	/**
	 * 获取redis存储的key
	 * @param roomId
	 * @param validType
	 * @return
	 */
	public String getRedisTokenKey(String roomId, ValidType validType) {
		if (validType == ValidType.PULL) {
			return StrUtil.format(TOKEN_PULL, roomId);
		} else if (validType == ValidType.PUSH) {
			return StrUtil.format(TOKEN_PUSH, roomId);
		}
		return "";
	}

	/**
	 * token 时间戳校验
	 *
	 * @param request
	 * @param validType
	 * @return
	 */
	public Boolean judgeTimeStamp(VerifyRequest request, ValidType validType) {

		String redisToken = getRedisTokenKey(request.getRoomId(), validType);
		if (!StrUtil.isBlank(redisToken)) {
			Long expire = stringRedisTemplate.opsForValue().getOperations().getExpire(redisToken);
			if (expire == -2) {
				return Boolean.FALSE;
			}
			long current = DateUtil.current();
			long timeLimit=current+expire*1000;
			//改为10秒钟
			if(Math.abs(timeLimit-request.getExpireTimestamp())<=10000){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * token 校验token
	 * @param request
	 * @param validType
	 * @return
	 */
	public Boolean judgeToken(VerifyRequest request, ValidType validType) {

		String redisToken = getRedisTokenKey(request.getRoomId(), validType);
		if (!StrUtil.isBlank(redisToken)) {
			//token加密是否正常校验
			String origin = stringRedisTemplate.opsForValue().get(redisToken);
			if (!StrUtil.isBlank(origin)) {
				TokenEntity redisTokenEntity = buildToken(origin, request.getExpireTimestamp());
				//token校验
				if (Objects.equals(request.getToken(),redisTokenEntity.getToken())) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
}
