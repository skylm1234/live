package com.gejian.live.web.verification.push;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gejian.common.core.exception.BusinessException;
import com.gejian.live.common.enums.error.LiveBroadcastErrorCode;
import com.gejian.live.dao.entity.AnchorRoom;
import com.gejian.live.web.service.AnchorRoomService;
import com.gejian.live.web.verification.AbstractValidator;
import com.gejian.live.web.verification.Valid;
import com.gejian.live.web.verification.ValidType;
import com.gejian.live.web.verification.VerifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 17:35
 * @description：是否开通直播权限校验
 */

@Service
@Valid(type = {ValidType.PUSH},order = 1)
@Slf4j
public class PushStreamerValidator extends AbstractValidator {


	@Autowired
	private AnchorRoomService anchorRoomService;

	@Override
	public void valid(VerifyRequest request) {

		int count = anchorRoomService.count(Wrappers.lambdaQuery(AnchorRoom.class)
				.eq(AnchorRoom::isDeleted, false)
				.eq(AnchorRoom::getAnchorStatus, false)
				.eq(AnchorRoom::getLiveStatus, false)
				.eq(AnchorRoom::getRoomId, request.getRoomId())
		);
		if(count==0){
			throw new BusinessException(LiveBroadcastErrorCode.BROADCAST_PERMISSION_FAIL);
		}

		log.info("PushStreamerValidator valid result {}");
	}
}
