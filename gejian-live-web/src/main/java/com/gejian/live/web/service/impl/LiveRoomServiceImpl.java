package com.gejian.live.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.live.dao.entity.LiveRoom;
import com.gejian.live.dao.mapper.LiveRoomMapper;
import com.gejian.live.web.service.LiveRoomService;
import org.springframework.stereotype.Service;

/**
 * @author fengliang
 * @Date 2021/9/16
 * @description:
 */
@Service
public class LiveRoomServiceImpl extends ServiceImpl<LiveRoomMapper, LiveRoom> implements LiveRoomService {

	@Override
	public boolean updateByVersionAndId(LiveRoom one) {
		return this.baseMapper.updateByVersionAndId(one) > 0;
	}
}
