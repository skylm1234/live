package com.gejian.live.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.live.dao.entity.StreamerOffline;
import com.gejian.live.dao.entity.StreamerOnline;

/**
 *  Auto created by codeAppend plugin
 */
public interface StreamerOfflineService extends IService<StreamerOffline> {
	//主播结束直播
	void StreamerEnd(Long userId);

	//添加主播线上直播记录
	Boolean saveStreamerOffline(StreamerOnline streamerOnline);
}