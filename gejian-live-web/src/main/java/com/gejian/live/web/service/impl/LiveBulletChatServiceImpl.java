package com.gejian.live.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.common.core.util.acautomation.ACAutomationSearch;
import com.gejian.common.core.util.acautomation.ReplacePolicy;
import com.gejian.live.dao.entity.LiveBulletChat;
import com.gejian.live.dao.mapper.LiveBulletChatMapper;
import com.gejian.live.web.service.LiveBulletChatService;
import com.gejian.live.web.service.mq.producer.BulletChatProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：lijianghuai
 * @date ：2021-09-22 16:47
 * @description：
 */
@Service
public class LiveBulletChatServiceImpl extends ServiceImpl<LiveBulletChatMapper, LiveBulletChat> implements LiveBulletChatService {

	@Autowired
	BulletChatProducer bulletChatProducer;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean save(LiveBulletChat entity) {
		ACAutomationSearch.SearchResult searchResult = ACAutomationSearch.getInstance().search(entity.getContent(),new ReplacePolicy('*'));
		entity.setContent(searchResult.getText());
		this.getBaseMapper().insert(entity);
		bulletChatProducer.producer(entity);
		return true;
	}
}
