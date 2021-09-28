package com.gejian.live.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.common.core.exception.BusinessException;
import com.gejian.live.common.dto.AnchorRoomDTO;
import com.gejian.live.common.enums.error.NotFoundErrorCode;
import com.gejian.live.dao.entity.Anchor;
import com.gejian.live.dao.entity.AnchorRoom;
import com.gejian.live.dao.mapper.AnchorMapper;
import com.gejian.live.dao.mapper.AnchorRoomMapper;
import com.gejian.live.web.service.AnchorRoomService;
import com.gejian.live.web.service.LiveRoomHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author yuanxue
 * @Date 2021/9/22
 * @description: 直播申请审核
 */
@Service
public class AnchorRoomServiceImpl extends ServiceImpl<AnchorRoomMapper, AnchorRoom> implements AnchorRoomService {

	@Autowired
	private AnchorMapper anchorMapper;
	@Autowired
	private LiveRoomHelper liveRoomHelper;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean saveAnchorRoom(AnchorRoomDTO anchorRoomDTO) {
		Anchor anchor = Optional.ofNullable(anchorMapper.selectById(anchorRoomDTO.getAnchorId())).orElseThrow(() -> new BusinessException(NotFoundErrorCode.INSTANCE));
		anchor.setStatus(anchorRoomDTO.getCheck());
		anchorMapper.updateById(anchor);
		AnchorRoom anchorRoom = BeanUtil.copyProperties(anchorRoomDTO, AnchorRoom.class);
		anchorRoom.setUserId(anchor.getUserId());
		anchorRoom.setRoomDescription(anchor.getRoomDescription());
		anchorRoom.setRoomTitle(anchor.getRoomTitle());
		anchorRoom.setRoomId(Integer.valueOf(liveRoomHelper.getRandomLiveRoom().getRoomId()));
		return this.save(anchorRoom);
	}

}