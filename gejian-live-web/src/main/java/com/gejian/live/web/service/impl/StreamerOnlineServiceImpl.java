package com.gejian.live.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.live.common.dto.streamer_online.StreamerOnlineAdd;
import com.gejian.live.dao.entity.StreamerOnline;
import com.gejian.live.dao.mapper.StreamerOnlineMapper;
import com.gejian.live.web.service.StreamerOnlineService;
import org.springframework.stereotype.Service;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class StreamerOnlineServiceImpl extends ServiceImpl<StreamerOnlineMapper, StreamerOnline> implements StreamerOnlineService {

	// use "baseMapper" to call jdbc
	// example: baseMapper.insert(entity);
	// example: baseMapper.selectByPage(params);


	@Override
	public void StreamerStart() {
		//TODO 主播开始直播 修改直播房间直播状态

		//TODO 添加主播直播线上记录
		saveStreamerOnline(null);
	}

	@Override
	public Boolean saveStreamerOnline(StreamerOnlineAdd streamerOnlineAdd) {
		StreamerOnline streamerOnline = new StreamerOnline();
		streamerOnline.setClientId(streamerOnlineAdd.getClientId());
		streamerOnline.setIp(streamerOnlineAdd.getIp());
		streamerOnline.setRoomCode(streamerOnlineAdd.getRoomCode());
		streamerOnline.setUserId(streamerOnlineAdd.getUserId());
		return this.save(streamerOnline);
	}
}