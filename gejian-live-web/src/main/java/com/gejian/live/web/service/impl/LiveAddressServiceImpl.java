package com.gejian.live.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gejian.common.security.service.GeJianUser;
import com.gejian.common.security.util.SecurityUtils;
import com.gejian.live.common.dto.PullFlowAddressDTO;
import com.gejian.live.common.dto.PullFlowAddressResponseDTO;
import com.gejian.live.common.dto.PushFlowAddressResponseDTO;
import com.gejian.live.dao.entity.AnchorRoom;
import com.gejian.live.dao.entity.TokenEntity;
import com.gejian.live.web.config.ServerComponent;
import com.gejian.live.web.service.AnchorRoomService;
import com.gejian.live.web.service.LiveAddressService;
import com.gejian.live.web.service.TokenService;
import com.gejian.live.web.verification.ValidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuanxue
 * @Date 2021/9/22
 * @description: 获取推拉流
 */
@Component
public class LiveAddressServiceImpl implements LiveAddressService {

	@Autowired
	private AnchorRoomService anchorRoomService;
	@Autowired
	private ServerComponent serverComponent;
	@Autowired
	private TokenService tokenService;

	@Override
	public PushFlowAddressResponseDTO getPushFlowAddress() {
		final GeJianUser user = SecurityUtils.getUser();
		AnchorRoom anchorRoom = anchorRoomService.getOne(Wrappers.lambdaQuery(AnchorRoom.class)
				.eq(AnchorRoom::getDeleted, 0)
				.eq(AnchorRoom::getUserId, user.getId()));
		String address = serverComponent.getRtmpUrl();
		TokenEntity tokenEntity = tokenService.generateTokenSalt(anchorRoom.getRoomId().toString(), ValidType.PUSH);
		PushFlowAddressResponseDTO addressDTO = new PushFlowAddressResponseDTO();
		addressDTO.setAddress(address);
		addressDTO.setStreamingCode(anchorRoom.getRoomId() + "?token=" + tokenEntity.getToken() + "&expire=" + tokenEntity.getExpireTimestamp());
		return addressDTO;
	}

	@Override
	public PullFlowAddressResponseDTO getPullFlowAddress(PullFlowAddressDTO pullFlowAddressDTO) {
		PullFlowAddressResponseDTO addressDTO = new PullFlowAddressResponseDTO();
		TokenEntity tokenEntity = tokenService.generateTokenSalt(pullFlowAddressDTO.getRoomId().toString(), ValidType.PULL);
		String rtmpUrl = serverComponent.getRtmpUrl();
		String hlsUrl = serverComponent.getm3u8Url(pullFlowAddressDTO.getRoomId().toString());
		addressDTO.setRtmpAddress(rtmpUrl + "/" + pullFlowAddressDTO.getRoomId() + "?token=" + tokenEntity.getToken() + "?expire=" + tokenEntity.getExpireTimestamp());
		addressDTO.setHlsAddress(hlsUrl + "?token=" + tokenEntity.getToken() + "?expire=" + tokenEntity.getExpireTimestamp());
		return addressDTO;
	}
}