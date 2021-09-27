package com.gejian.live.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.live.common.dto.streamer.StreamerOnlineAdd;
import com.gejian.live.dao.entity.StreamerOnline;
import com.gejian.live.dao.mapper.StreamerOnlineMapper;
import com.gejian.live.web.service.StreamerOnlineService;
import com.gejian.live.web.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class StreamerOnlineServiceImpl extends ServiceImpl<StreamerOnlineMapper, StreamerOnline> implements StreamerOnlineService {

	// use "baseMapper" to call jdbc
	// example: baseMapper.insert(entity);
	// example: baseMapper.selectByPage(params);

	@Autowired
	private TaskService taskService;

	@Override
	public void StreamerStart(Long userId, String clientId, String ip, Integer roomCode) {
		//TODO 主播开始直播 修改直播房间直播状态

		//TODO 添加主播直播线上记录
		StreamerOnlineAdd streamerOnlineAdd = new StreamerOnlineAdd();
		streamerOnlineAdd.setUserId(userId);
		streamerOnlineAdd.setIp(ip);
		streamerOnlineAdd.setClientId(clientId);
		streamerOnlineAdd.setRoomCode(roomCode);
		saveStreamerOnline(streamerOnlineAdd);
	}

	@Override
	public Boolean saveStreamerOnline(StreamerOnlineAdd streamerOnlineAdd) {
		StreamerOnline streamerOnline = new StreamerOnline();
		streamerOnline.setClientId(streamerOnlineAdd.getClientId());
		streamerOnline.setIp(streamerOnlineAdd.getIp());
		streamerOnline.setRoomCode(streamerOnlineAdd.getRoomCode());
		streamerOnline.setUserId(streamerOnlineAdd.getUserId());

		//创建定时任务并启动
		Integer jobId = taskService.addAndStart(streamerOnline.getRoomCode());
		streamerOnline.setJobId(jobId);

		return this.save(streamerOnline);
	}

	@Override
	public StreamerOnline getByUserId(Long userId) {

		LambdaQueryWrapper<StreamerOnline> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(StreamerOnline::getUserId, userId);

		return this.getOne(wrapper);
	}
}