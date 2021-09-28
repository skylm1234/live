package com.gejian.live.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.live.common.constants.LiveRedisConstant;
import com.gejian.live.common.dto.streamer.StreamerOnlineAdd;
import com.gejian.live.dao.entity.StreamerOnline;
import com.gejian.live.dao.mapper.StreamerOnlineMapper;
import com.gejian.live.web.service.AnchorRoomService;
import com.gejian.live.web.service.StreamerOnlineService;
import com.gejian.live.web.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
	@Autowired
	private AnchorRoomService anchorRoomService;
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public void StreamerStart(Long userId, String clientId, String ip, Integer roomId) {
		//主播开始直播 修改直播房间直播状态
		anchorRoomService.changeLiveStatus(userId, true);

		//添加主播直播线上记录
		StreamerOnlineAdd streamerOnlineAdd = new StreamerOnlineAdd();
		streamerOnlineAdd.setUserId(userId);
		streamerOnlineAdd.setIp(ip);
		streamerOnlineAdd.setClientId(clientId);
		streamerOnlineAdd.setRoomId(roomId);
		saveStreamerOnline(streamerOnlineAdd);
	}

	@Override
	public Boolean saveStreamerOnline(StreamerOnlineAdd streamerOnlineAdd) {

		StreamerOnline streamerOnline = new StreamerOnline();
		streamerOnline.setClientId(streamerOnlineAdd.getClientId());
		streamerOnline.setIp(streamerOnlineAdd.getIp());
		streamerOnline.setRoomId(streamerOnlineAdd.getRoomId());
		streamerOnline.setUserId(streamerOnlineAdd.getUserId());
		//TODO 设置是否手动设置封面,默认设置按照截图方式
		streamerOnline.setRoomCoverType(false);

		//创建定时任务并启动
		Integer jobId = taskService.addAndStart(streamerOnline.getRoomId());
		streamerOnline.setJobId(jobId);

		boolean save = this.save(streamerOnline);

		//redis缓存封面设置方式
		redisTemplate.opsForValue().set(LiveRedisConstant.liveCoverType + streamerOnline.getRoomId(), "false");

		return save;
	}

	@Override
	public StreamerOnline getByUserId(Long userId) {

		LambdaQueryWrapper<StreamerOnline> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(StreamerOnline::getUserId, userId);

		return this.getOne(wrapper);
	}

	@Override
	public StreamerOnline getByRoomId(Integer roomId) {
		LambdaQueryWrapper<StreamerOnline> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(StreamerOnline::getRoomId, roomId);

		return this.getOne(wrapper);
	}

	@Override
	public boolean updateCover(Integer roomId, String roomCoverFileName, String roomCoverFileBucketName) {
		LambdaQueryWrapper<StreamerOnline> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(StreamerOnline::getRoomId, roomId);

		StreamerOnline streamerOnline = new StreamerOnline();
		streamerOnline.setRoomCoverFileName(roomCoverFileName);
		streamerOnline.setRoomCoverFileBucketName(roomCoverFileBucketName);

		return this.update(streamerOnline, wrapper);
	}
}