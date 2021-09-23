package com.gejian.live.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.live.common.dto.streamer_online.StreamerOnlineAdd;
import com.gejian.live.dao.entity.StreamerOnline;

/**
 *  Auto created by codeAppend plugin
 */
public interface StreamerOnlineService extends IService<StreamerOnline> {

	void StreamerStart();

	//添加主播线上直播记录
 	Boolean saveStreamerOnline(StreamerOnlineAdd streamerOnlineAdd);

}