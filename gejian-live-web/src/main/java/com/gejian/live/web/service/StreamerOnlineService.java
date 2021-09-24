package com.gejian.live.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.live.common.dto.streamer.StreamerOnlineAdd;
import com.gejian.live.dao.entity.StreamerOnline;

/**
 *  Auto created by codeAppend plugin
 */
public interface StreamerOnlineService extends IService<StreamerOnline> {

	void StreamerStart(Long userId, String clientId, String ip, Integer roomCode);

	/**
	 * 添加主播线上直播记录
	 * @param streamerOnlineAdd
	 * @return
	 */
 	Boolean saveStreamerOnline(StreamerOnlineAdd streamerOnlineAdd);
	/**
	 * 根据userId获取线上直播信息
	 */
	StreamerOnline getByUserId(Long userId);
}