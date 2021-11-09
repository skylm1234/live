package com.gejian.live.web.event;

import com.gejian.live.web.action.params.PublishActionParams;
import com.gejian.live.web.service.StreamerOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：lijianghuai
 * @date ：2021-11-08 15:23
 * @description：
 */

@Component
public class LivePublishedEventExecutor implements LiveApplicationEventExecutor<LivePublishedEvent>{

	@Autowired
	private StreamerOnlineService streamerOnlineService;

	@Override
	public void execute(LivePublishedEvent livePublishedEvent){
		PublishActionParams actionParams = (PublishActionParams) livePublishedEvent.getActionParams();
		streamerOnlineService.StreamerStart( actionParams.getClient_id(), actionParams.getIp(), Integer.parseInt(actionParams.getStream()));
	}
}
