package com.gejian.live.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.live.common.dto.watch.UserWatchOnlineSaveDTO;
import com.gejian.live.dao.entity.UserWatchOnline;
import com.gejian.live.dao.mapper.UserWatchOnlineMapper;
import com.gejian.live.web.service.UserWatchOnlineService;
import org.springframework.stereotype.Service;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class UserWatchOnlineServiceImpl extends ServiceImpl<UserWatchOnlineMapper, UserWatchOnline> implements UserWatchOnlineService {

	// use "baseMapper" to call jdbc
	// example: baseMapper.insert(entity);
	// example: baseMapper.selectByPage(params);


	@Override
	public Boolean saveUserWatchOnline(UserWatchOnlineSaveDTO userWatchOnlineSaveDTO) {

		UserWatchOnline userWatchOnline = BeanUtil.copyProperties(userWatchOnlineSaveDTO, UserWatchOnline.class);

		return this.save(userWatchOnline);
	}

	@Override
	public UserWatchOnline findByUserIdAndRoomCode(Long userId, Integer roomId) {
		LambdaQueryWrapper<UserWatchOnline> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(UserWatchOnline::getRoomId, roomId)
				.eq(UserWatchOnline::getUserId, userId);
		return this.getOne(wrapper);
	}
}