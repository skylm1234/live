package com.gejian.live.web.service;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gejian.common.core.constant.SecurityConstants;
import com.gejian.common.core.exception.BusinessException;
import com.gejian.leaf.client.feign.RemoteLeafService;
import com.gejian.live.common.enums.error.LiveRoomErrorCode;
import com.gejian.live.dao.entity.LiveRoom;
import com.gejian.live.web.event.LiveRoomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author fengliang
 * @Date 2021/9/16
 * @description:
 */
@Component
@Slf4j
public class LiveRoomHelper implements InitializingBean {
    /**
	 * 房间号长度
     */
    private static final Integer ROOM_NUMBER_LENGTH=8;
    /**
	 * 每次生成房间号数量
     */
    private static final Integer INIT_ROOM_NUMBER=10;
    /**
	 * 阈值
     */
    private static final Integer THRESHOLD=4;
    /**
	 * 重试生成次数
     */
    private static final int RETRY_NUMBER=2;

    private int retry=0;
    /**
	 * 实例名称
     */
    private static final String INSTANCES ="INSTANCES";
	/**
	 * leaf配置key_words
	 */
	private static final String KEY_WORDS="live-room";


	@Autowired
	private ApplicationEventPublisher eventPublisher;

    @Autowired
    private LiveRoomService liveRoomService;

    @Autowired
	private RemoteLeafService remoteLeafService;


    /**
     * 扩容方法
     */
    public void resize(){

        for (int i = 0; i < INIT_ROOM_NUMBER; i++) {
            String roomId = RandomUtil.randomNumbers(ROOM_NUMBER_LENGTH);
			LiveRoom liveRoom = new LiveRoom();
//			log.info("======================={}",remoteLeafService.getSnowflakeId(KEY_WORDS,SecurityConstants.FROM_IN));
            liveRoom.setRoomId(remoteLeafService.getSegmentId(KEY_WORDS,SecurityConstants.FROM_IN));
            //todo 正则表达式判断是否是靓号
			liveRoom.setIsBeautifulNumber(false);
			liveRoom.setIsOccupy(false);
			liveRoom.setDeleted(false);
			liveRoom.setVersion(1);
            try {
                liveRoomService.save(liveRoom);
            }catch (DuplicateKeyException e){
                log.error("生成的房间号在数据库中存在===roomId[{}]",roomId);
            }

        }

    }

    /**
     * 判断是否达到阈值 达到阈值再次生成
     *
     */
    public void compensate(){
    	retry=0;
        int count = liveRoomService.count(Wrappers.lambdaQuery(LiveRoom.class)
                .eq(LiveRoom::getDeleted, false)
                .eq(LiveRoom::getIsBeautifulNumber, false)
                .eq(LiveRoom::getIsOccupy, false)
        );
        while (count<THRESHOLD){
            resize();
            count=liveRoomService.count(Wrappers.lambdaQuery(LiveRoom.class)
                    .eq(LiveRoom::getDeleted, false)
                    .eq(LiveRoom::getIsBeautifulNumber, false)
                    .eq(LiveRoom::getIsOccupy, false)
            );
            retry++;
            log.info("==扩容次数=={}==扩容时间=={}=======================================",retry,LocalDateTime.now());
            if(retry>RETRY_NUMBER){
                log.error("系统房间号长度已达上限，请尽快扩容房间号长度");
                //todo 邮件通知管理员扩展房间号长度
                retry=0;
                break;
            }
        }

    }


    /**
     * 消耗一个房间号
     */
    public LiveRoom getRandomLiveRoom(){
        Map<String, LiveRoom> resultMap = new HashMap<>();
        recursionRegisterRoom(resultMap);
        return resultMap.get(INSTANCES);

    }

	/**
	 * 递归查找数据库房间号
     * @param map
	 * @return
	 */
    private void recursionRegisterRoom(Map<String, LiveRoom> map) {
        //拿到数据库第一条数据
		LambdaQueryWrapper<LiveRoom> queryWrapper = Wrappers.lambdaQuery(LiveRoom.class)
				.eq(LiveRoom::getDeleted, false)
				.eq(LiveRoom::getIsBeautifulNumber, false)
				.eq(LiveRoom::getIsOccupy, false)
				.last("limit 1");
		LiveRoom one = Optional.ofNullable(liveRoomService.getOne(queryWrapper)).orElseGet(() -> {
			resize();
			return liveRoomService.getOne(queryWrapper);
		});
		if(Objects.isNull(one)){
            log.error("数据库中已没有多余的房间号了");
            throw new BusinessException(LiveRoomErrorCode.ROOM_FULL_FAIL);
        }
        boolean update = liveRoomService.updateByVersionAndId(one);
        if(update){
            map.put(INSTANCES,one);
			eventPublisher.publishEvent(new LiveRoomEvent(new Object()));
        }else {
            recursionRegisterRoom(map);
        }

    }


	@Override
	public void afterPropertiesSet() {
		compensate();
	}



}
