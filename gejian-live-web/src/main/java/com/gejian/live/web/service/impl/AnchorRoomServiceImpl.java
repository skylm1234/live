package com.gejian.live.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.live.common.dto.AnchorRoomDTO;
import com.gejian.live.dao.entity.Anchor;
import com.gejian.live.dao.entity.AnchorRoom;
import com.gejian.live.dao.mapper.AnchorRoomMapper;
import com.gejian.live.web.service.AnchorRoomService;
import com.gejian.live.web.service.AnchorService;
import com.gejian.live.web.service.LiveRoomHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuanxue
 * @Date 2021/9/22
 * @description: 直播申请直播Service实现类
 */
@Service
public class AnchorRoomServiceImpl extends ServiceImpl<AnchorRoomMapper, AnchorRoom> implements AnchorRoomService {

	@Autowired
	private AnchorService anchorService;
	@Autowired
	private LiveRoomHelper liveRoomHelper;

	@Override
	public Boolean saveAnchorRoom(AnchorRoomDTO anchorRoomDTO) {
		Anchor anchor = new Anchor();
		anchor.setStatus(1);
		anchor.setId(anchorRoomDTO.getAnchorId());
		anchorService.updateById(anchor);
		AnchorRoom anchorRoom = BeanUtil.copyProperties(anchorRoomDTO, AnchorRoom.class);
		//todo 默认状态为可直播状态
		anchorRoom.setAnchorStatus(1);
		anchorRoom.setLiveStatus(1);
		anchorRoom.setRoomId(Integer.valueOf(liveRoomHelper.getRandomLiveRoom().getRoomId()));
		return this.save(anchorRoom);
	}

	@Override
	public Integer getByUserId(Long userId) {
		return baseMapper.getByUserId(userId);
	}
}