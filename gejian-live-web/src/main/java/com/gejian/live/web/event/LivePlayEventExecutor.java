package com.gejian.live.web.event;

import com.gejian.live.common.dto.watch.UserWatchOnlineSaveDTO;
import com.gejian.live.web.action.params.PlayActionParams;
import com.gejian.live.web.service.UserWatchOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：lijianghuai
 * @date ：2021-11-08 15:23
 * @description：
 */

@Component
public class LivePlayEventExecutor implements LiveApplicationEventExecutor<LivePlayEvent>{

	@Autowired
	private UserWatchOnlineService userWatchOnlineService;

	@Override
	public void execute(LivePlayEvent livePlayEvent){
		//TODO 用户播放流的时候记录用户基本信息
		PlayActionParams actionParams = (PlayActionParams) livePlayEvent.getActionParams();
		userWatchOnlineService.saveUserWatchOnline(new UserWatchOnlineSaveDTO());
	}
}
