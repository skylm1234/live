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
	 * 生成随机token
	 *
	 * @param roomId
	 * @param validType
	 * @return
	 */
	public TokenEntity generateTokenSalt(String roomId, ValidType validType) {
		TokenEntity tokenEntity = new TokenEntity();
		String originToken = RandomUtil.randomString(TOKEN_LENGTH);
		Long tomorrowTimeMillis = getTomorrowTimeMillis();
		digester.setSalt(String.valueOf(tomorrowTimeMillis).getBytes());
		String token = digester.digestHex(originToken);
		tokenEntity.setToken(token);
		tokenEntity.setTimestamp(tomorrowTimeMillis);
		log.info("接口自动生成的：源字符串["+originToken+"]=====时间戳["+tomorrowTimeMillis+"]===加密字符串["+token+"]");
		if (validType == ValidType.PULL) {
			//拉流
			String pullKey = StrUtil.format(TOKEN_PULL, roomId);
			stringRedisTemplate.opsForValue().set(pullKey, originToken, TOKEN_PULL_TIME, TimeUnit.MINUTES);
			return tokenEntity;
		} else if (validType == ValidType.PUSH) {
			//推流
			String pushKey = StrUtil.format(TOKEN_PUSH, roomId);
			stringRedisTemplate.opsForValue().set(pushKey, originToken, TOKEN_PUSH_TIME, TimeUnit.MINUTES);
			return tokenEntity;
		}
		return null;

	}

	/**
	 * 根据 原token和时间戳生成md5 Token
	 * @param roomId
	 * @param timestamp
	 * @param validType
	 * @return
	 */
	public TokenEntity generateTokenByTimestampAndToken(String roomId,Long timestamp ,ValidType validType) {

		String redisTokenKey = getRedisTokenKey(roomId, validType);
		String originToken = stringRedisTemplate.opsForValue().get(redisTokenKey);
		digester.setSalt(String.valueOf(timestamp).getBytes());
		String token = digester.digestHex(originToken);
		TokenEntity tokenEntity = new TokenEntity(token, timestamp);
		log.info("前端入参生成的：源字符串["+originToken+"]=====时间戳["+timestamp+"]===加密字符串["+token+"]");

		return tokenEntity;

	}

	/**
	 * token 校验时间戳和token
	 * @param roomId
	 * @param validType
	 * @param tokenEntity
	 * @return
	 */
	public Boolean judgeTokenAndTimeStamp(String roomId, ValidType validType, TokenEntity tokenEntity) {
		if (StrUtil.isBlank(tokenEntity.getToken()) || null == tokenEntity.getTimestamp()) {
			return Boolean.FALSE;
		}
		String redisToken = getRedisTokenKey(roomId, validType);
		if (!StrUtil.isBlank(redisToken)) {
			Long expire = stringRedisTemplate.opsForValue().getOperations().getExpire(redisToken);
			if (expire == -2) {
				return Boolean.FALSE;
			}
			//token过期时间校验
			boolean lessOrEqual = false;
			long current = DateUtil.current();
			long timeLimit=current+expire*1000;
			if(Math.abs(timeLimit-tokenEntity.getTimestamp())<=1000){
				lessOrEqual=true;
			}
//			if(timeLimit>=tokenEntity.getTimestamp()){
//				lessOrEqual=true;
//			}else {
//				//判断是不是在1秒类
//				long cha=tokenEntity.getTimestamp()-timeLimit;
//				if(cha<1000){
//					lessOrEqual=true;
//				}
//			}
			if (lessOrEqual) {
				//token加密是否正常校验
				String origin = stringRedisTemplate.opsForValue().get(redisToken);
				if (!StrUtil.isBlank(origin)) {
					TokenEntity redisTokenEntity = generateTokenByTimestampAndToken(roomId, tokenEntity.getTimestamp(), validType);
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

		String redisToken = getRedisTokenKey(String.valueOf(request.getRoomId()), validType);
		if (!StrUtil.isBlank(redisToken)) {
			Long expire = stringRedisTemplate.opsForValue().getOperations().getExpire(redisToken);

			if (expire == -2) {
				return Boolean.FALSE;
			}
			long current = DateUtil.current();
			long timeLimit=current+expire*1000;
			log.info("参数时间["+request.getExpireTimestamp()+"]==============最大时间["+timeLimit+"]");
			if(Math.abs(timeLimit-request.getExpireTimestamp())<=1000){
				return Boolean.TRUE;
			}
//			if(timeLimit>=request.getExpireTimestamp()){
//				log.info("参数时间["+request.getExpireTimestamp()+"]==============最大时间["+timeLimit+"]");
//				return Boolean.TRUE;
//			}else {
//				//判断是不是在1秒类
//				long cha=request.getExpireTimestamp()-timeLimit;
//				log.info("参数时间["+request.getExpireTimestamp()+"]==============最大时间["+timeLimit+"]");
//				log.info("相差时间=="+cha);
//				if(cha<1000){
//					return Boolean.TRUE;
//				}
//
//			}

			//token过期时间校验

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

		String redisToken = getRedisTokenKey(String.valueOf(request.getRoomId()), validType);
		if (!StrUtil.isBlank(redisToken)) {
			//token加密是否正常校验
			String origin = stringRedisTemplate.opsForValue().get(redisToken);
			if (!StrUtil.isBlank(origin)) {
				TokenEntity redisTokenEntity = generateTokenByTimestampAndToken(String.valueOf(request.getRoomId()), request.getExpireTimestamp(), validType);
				//token校验
				if (Objects.equals(request.getToken(),redisTokenEntity.getToken())) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
}
