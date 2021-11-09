package com.gejian.live.web.service.impl;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.common.core.exception.BusinessException;
import com.gejian.common.minio.enums.Bucket;
import com.gejian.common.minio.service.GeJianMinio;
import com.gejian.live.common.constants.LiveRedisConstant;
import com.gejian.live.common.dto.SnapshotInfo;
import com.gejian.live.common.enums.error.LiveJobErrorCode;
import com.gejian.live.dao.entity.StreamerOffline;
import com.gejian.live.dao.entity.StreamerOnline;
import com.gejian.live.dao.mapper.StreamerOfflineMapper;
import com.gejian.live.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
	@Autowired
	private AnchorRoomService anchorRoomService;
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public void StreamerEnd(Integer roomId) {
		//修改直播状态为未开播
		anchorRoomService.changeLiveStatus(roomId, false);

		//将线上播放记录拷贝到历史播放记录
		StreamerOnline streamerOnline = streamerOnlineService.getByUserId(roomId);
		saveStreamerOffline(streamerOnline);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveStreamerOffline(StreamerOnline streamerOnline) {
		StreamerOffline offline = new StreamerOffline();
		offline.setRoomId(streamerOnline.getRoomId());
		offline.setClientId(streamerOnline.getClientId());
		offline.setIp(streamerOnline.getIp());
		offline.setCreateTime(streamerOnline.getCreateTime());

		if (streamerOnline.getRoomCoverType()) {
			//获取自己设置的封面图片
			offline.setRoomCoverFileName(streamerOnline.getRoomCoverFileName());
			offline.setRoomCoverFileBucketName(streamerOnline.getRoomCoverFileBucketName());
		} else {
			Optional<SnapshotInfo> optional = imageService.snapShot(streamerOnline.getRoomId());
			if (optional.isPresent()) {
				SnapshotInfo snapshotInfo = optional.get();
				//byte转InputStream
				try {
					geJianMinio.putObject(Bucket.PUBLIC, snapshotInfo.getFileName(), IoUtil.toStream(snapshotInfo.getBytes()));
				} catch (Exception e) {
					log.error("直播封面上传截图失败", e);
					throw new BusinessException(LiveJobErrorCode.UPLOAD_PICTURE_FAILURE);
				}
				offline.setRoomCoverFileName(snapshotInfo.getFileName());
				offline.setRoomCoverFileBucketName(Bucket.PUBLIC.toString());
			}
		}

		//保存历史记录
		this.save(offline);
		//删除线上记录
		boolean result = streamerOnlineService.removeById(streamerOnline.getId());
		//删除定时任务
		taskService.remove(streamerOnline.getJobId());
		//删除redis封面设置方式的缓存
		redisTemplate.delete(LiveRedisConstant.liveCoverType + offline.getRoomId());

		return result;
	}
}