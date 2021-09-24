package com.gejian.live.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.live.common.dto.watch.UserWatchOnlineSaveDTO;
import com.gejian.live.dao.entity.UserWatchOffline;

/**
 *  Auto created by codeAppend plugin
 */
public interface UserWatchOfflineService extends IService<UserWatchOffline> {

	Boolean saveUserWatchOffline(Long userId,Integer roomCode);

}