package com.gejian.live.web.service.job;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.gejian.common.core.exception.BusinessException;
import com.gejian.common.core.util.UCloudPictureCensorUtils;
import com.gejian.common.minio.enums.Bucket;
import com.gejian.common.minio.service.GeJianMinio;
import com.gejian.live.common.constants.LiveRedisConstant;
import com.gejian.live.common.dto.LiveJobDTO;
import com.gejian.live.common.dto.SnapshotInfo;
import com.gejian.live.common.enums.error.LiveJobErrorCode;
import com.gejian.live.dao.entity.StreamerOnline;
import com.gejian.live.web.service.ImageService;
import com.gejian.live.web.service.StreamerOnlineService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author:chen
 * @Date: 2021/9/28 10:17
 */
@Component
@Slf4j
public class IdentifyPornographicImagesHandler {

	@Autowired
	private ImageService imageService;
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private StreamerOnlineService streamerOnlineService;
	@Autowired
	private GeJianMinio geJianMinio;

	/**
	 * 直播鉴黄定时任务
	 *
	 * @return
	 */
	@XxlJob("LiveImagesHandler")
	public ReturnT<String> execute() {
		String jobParam = XxlJobHelper.getJobParam();

		if (StrUtil.isBlank(jobParam)) {
			log.error(LiveJobErrorCode.NOT_FOUND_PARAM.getMsg());
			throw new BusinessException(LiveJobErrorCode.NOT_FOUND_PARAM);
		}
		LiveJobDTO liveJobDTO = JSONUtil.toBean(jobParam, LiveJobDTO.class);

		//获取房间号
		Integer roomId = liveJobDTO.getRoomId();
		//获取截图
		Optional<SnapshotInfo> optional = imageService.snapShot(roomId);
		//调用第三方UCCloud的鉴黄服务
		if (optional.isPresent()) {
			SnapshotInfo snapshotInfo = optional.get();
			double censor = UCloudPictureCensorUtils.censor(snapshotInfo.getFileName(), snapshotInfo.getBytes());
			log.info("直播鉴黄返回结果{}", censor);

			//是否是自己设置直播截图
			if (!isSetCover(roomId)) {
				//byte转InputStream
				try {
					geJianMinio.putObject(Bucket.PUBLIC, snapshotInfo.getFileName(), IoUtil.toStream(snapshotInfo.getBytes()));
				} catch (Exception e) {
					log.error("直播封面上传截图失败", e);
					throw new BusinessException(LiveJobErrorCode.UPLOAD_PICTURE_FAILURE);
				}
				streamerOnlineService.updateCover(roomId, snapshotInfo.getFileName(), Bucket.PUBLIC.toString());
			}

			return null;
		}
		return null;
	}

	private boolean isSetCover(Integer roomId) {
		String coverTypeStr = redisTemplate.opsForValue().get(LiveRedisConstant.liveCoverType + roomId);

		boolean coverType;
		if (StrUtil.isNotBlank(coverTypeStr)) {
			coverType = Boolean.parseBoolean(coverTypeStr);
		} else {
			StreamerOnline online = streamerOnlineService.getByRoomId(roomId);
			coverType = online.getRoomCoverType();
			//redis缓存封面设置方式
			redisTemplate.opsForValue().set(LiveRedisConstant.liveCoverType + roomId, String.valueOf(coverType));
		}
		return coverType;
	}
}
