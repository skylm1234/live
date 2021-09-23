package com.gejian.live.web.service.mq.producer;

import com.gejian.live.dao.entity.LiveBulletChat;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ：lijianghuai
 * @date ：2021-09-22 17:09
 * @description：
 */
@Component
public class BulletChatProducer {

	@Value("${gejian.live.consumer.topic.bullet_chat}")
	private String bulletChatTopic;

	@Autowired
	private RocketMQTemplate rocketMQTemplate;

	public void producer(LiveBulletChat liveBulletChat){
		rocketMQTemplate.syncSend(bulletChatTopic,liveBulletChat);
	}
}
