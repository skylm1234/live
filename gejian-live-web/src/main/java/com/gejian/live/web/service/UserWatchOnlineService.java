package com.gejian.live.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.live.common.dto.streamer.StreamerOnlineAdd;
import com.gejian.live.common.dto.watch.UserWatchOnlineSaveDTO;
import com.gejian.live.dao.entity.UserWatchOnline;
import io.swagger.models.auth.In;

/**
 *  Auto created by codeAppend plugin
 */
public interface UserWatchOnlineService extends IService<UserWatchOnline> {

	Boolean saveUserWatchOnline(UserWatchOnlineSaveDTO userWatchOnlineSaveDTO);

	UserWatchOnline findByUserIdAndRoomCode(Long userId, Integer roomCode);
}