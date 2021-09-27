package com.gejian.live.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.common.minio.service.GeJianMinio;
import com.gejian.live.dao.entity.StreamerOffline;
import com.gejian.live.dao.entity.StreamerOnline;
import com.gejian.live.dao.mapper.StreamerOfflineMapper;
import com.gejian.live.web.service.ImageService;
import com.gejian.live.web.service.StreamerOfflineService;
import com.gejian.live.web.service.StreamerOnlineService;
import com.gejian.live.web.service.TaskService;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class StreamerOfflineServiceImpl extends ServiceImpl<StreamerOfflineMapper, StreamerOffline> implements StreamerOfflineService {

	// use "baseMapper" to call jdbc
	// example: baseMapper.insert(entity);
	// example: baseMapper.selectByPage(params);

	@Autowired
	private StreamerOnlineService streamerOnlineService;
	@Autowired
	private GeJianMinio geJianMinio;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ImageService imageService;

	@Override
	public void StreamerEnd(Long userId) {
		//TODO 修改直播状态

		//TODO 将线上播放记录拷贝到历史播放记录
		StreamerOnline streamerOnline = streamerOnlineService.getByUserId(userId);
		saveStreamerOffline(streamerOnline);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveStreamerOffline(StreamerOnline streamerOnline) {
		StreamerOffline offline = new StreamerOffline();
		offline.setUserId(streamerOnline.getUserId());
		offline.setRoomCode(streamerOnline.getRoomCode());
		offline.setClientId(streamerOnline.getClientId());
		offline.setIp(streamerOnline.getIp());
		offline.setCreateTime(streamerOnline.getCreateTime());

		//TODO 获取截图并更新在历史表
		imageService.snapShot(streamerOnline.getRoomCode());

		//保存历史记录
		this.save(offline);
		//删除线上记录
		boolean result = streamerOnlineService.removeById(streamerOnline.getId());
		//删除定时任务
		taskService.remove(streamerOnline.getJobId());

		return result;
	}
}