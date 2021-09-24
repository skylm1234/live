package com.gejian.live.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.live.common.dto.watch.UserWatchOnlineSaveDTO;
import com.gejian.live.dao.entity.UserWatchOffline;
import com.gejian.live.dao.entity.UserWatchOnline;
import com.gejian.live.dao.mapper.UserWatchOfflineMapper;
import com.gejian.live.web.service.UserWatchOfflineService;
import com.gejian.live.web.service.UserWatchOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class UserWatchOfflineServiceImpl extends ServiceImpl<UserWatchOfflineMapper, UserWatchOffline> implements UserWatchOfflineService {

	// use "baseMapper" to call jdbc
	// example: baseMapper.insert(entity);
	// example: baseMapper.selectByPage(params);

	@Autowired
	private UserWatchOnlineService userWatchOnlineService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveUserWatchOffline(Long userId, Integer roomCode) {
		UserWatchOnline userWatchOnline = userWatchOnlineService.findByUserIdAndRoomCode(userId, roomCode);

		UserWatchOffline userWatchOffline = new UserWatchOffline();
		userWatchOffline.setUserId(userWatchOnline.getUserId());
		userWatchOffline.setClientId(userWatchOnline.getClientId());
		userWatchOffline.setCreateTime(userWatchOnline.getCreateTime());
		userWatchOffline.setIp(userWatchOnline.getIp());
		userWatchOffline.setRoomCode(userWatchOnline.getRoomCode());

		//保存历史记录
		this.save(userWatchOffline);
		//删除线上记录
		boolean result = userWatchOnlineService.removeById(userWatchOnline.getId());

		return result;
	}
}