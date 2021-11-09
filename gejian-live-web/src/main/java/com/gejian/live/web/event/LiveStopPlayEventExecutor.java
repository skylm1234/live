package com.gejian.live.web.event;

import com.gejian.live.web.action.params.StopPlayActionParams;
import com.gejian.live.web.service.UserWatchOfflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：lijianghuai
 * @date ：2021-11-08 15:23
 * @description：
 */

@Component
public class LiveStopPlayEventExecutor implements LiveApplicationEventExecutor<LiveStopPlayEvent>{

	@Autowired
	private UserWatchOfflineService userWatchOfflineService;

	@Override
	public void execute(LiveStopPlayEvent liveStopPlayEvent){
		//TODO 用户停止播放流的时候记录用户基本信息
		StopPlayActionParams actionParams = (StopPlayActionParams) liveStopPlayEvent.getActionParams();
		userWatchOfflineService.saveUserWatchOffline(null, null);
	}
}
