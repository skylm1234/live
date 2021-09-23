package com.gejian.live.web.service.mq.consumer;

import cn.hutool.json.JSONUtil;
import com.gejian.common.core.constant.SecurityConstants;
import com.gejian.common.mq.abstracts.AbstractRocketMQListener;
import com.gejian.im.client.feign.RemoteIMService;
import com.gejian.im.common.dto.BarrageMessageDTO;
import com.gejian.im.common.enums.TopicType;
import com.gejian.live.common.enums.LiveConsumeFailedType;
import com.gejian.live.dao.entity.LiveBulletChat;
import com.gejian.live.web.service.LiveBulletChatService;
import com.gejian.live.web.service.LiveConsumeFailedService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;

/**
 * @author ：lijianghuai
 * @date ：2021-09-22 17:16
 * @description：
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "${gejian.live.consumer.topic.bullet_chat}", consumerGroup = "${gejian.live.consumer.group.bullet_chat}")
public class BulletChatConsumer extends AbstractRocketMQListener<LiveBulletChat> {

	@Autowired
	private LiveBulletChatService liveBulletChatService;

	@Autowired
	private RemoteIMService remoteIMService;

	@Autowired
	private LiveConsumeFailedService liveConsumeFailedService;

	@Override
	public void consumer(LiveBulletChat liveBulletChat) {
		liveBulletChat = liveBulletChatService.getById(liveBulletChat.getId());
		BarrageMessageDTO barrageMessageDTO = new BarrageMessageDTO();
		barrageMessageDTO.setContent(liveBulletChat.getContent());
		barrageMessageDTO.setSenderId(liveBulletChat.getUserId());
		barrageMessageDTO.setSenderNickName(liveBulletChat.getNickname());
		barrageMessageDTO.setRoomId(liveBulletChat.getRoomId());
		barrageMessageDTO.setTopicType(TopicType.ONLINE_STREAM);
		barrageMessageDTO.setSecondsAt((int) liveBulletChat.getCreateTime().toEpochSecond(ZoneOffset.ofHours(8)));
		remoteIMService.sendToAll(barrageMessageDTO, SecurityConstants.FROM_IN);
	}

	@Override
	protected void onError(LiveBulletChat liveBulletChat, Exception e) {
		String jsonStr = JSONUtil.toJsonPrettyStr(liveBulletChat);
		liveConsumeFailedService.saveRecord(jsonStr, e, LiveConsumeFailedType.SEND_BULLET);
	}
}
