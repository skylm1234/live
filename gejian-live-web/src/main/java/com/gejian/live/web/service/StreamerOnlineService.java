package com.gejian.live.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.live.common.dto.streamer.StreamerOnlineAdd;
import com.gejian.live.dao.entity.StreamerOnline;

/**
 * Auto created by codeAppend plugin
 */
public interface StreamerOnlineService extends IService<StreamerOnline> {

	void StreamerStart(String clientId, String ip, Integer roomCode);

	/**
	 * 添加主播线上直播记录
	 *
	 * @param streamerOnlineAdd
	 * @return
	 */
	Boolean saveStreamerOnline(StreamerOnlineAdd streamerOnlineAdd);

	/**
	 * 根据房间号获取线上直播信息
	 */
	StreamerOnline getByUserId(Integer roomId);

	/**
	 * 根据房间号获取直播信息
	 *
	 * @param roomId
	 * @return
	 */
	StreamerOnline getByRoomId(Integer roomId);

	/**
	 * 修改直播间封面
	 * @param roomId
	 * @param roomCoverFileName
	 * @param roomCoverFileBucketName
	 * @return
	 */
	boolean updateCover(Integer roomId, String roomCoverFileName, String roomCoverFileBucketName);
}