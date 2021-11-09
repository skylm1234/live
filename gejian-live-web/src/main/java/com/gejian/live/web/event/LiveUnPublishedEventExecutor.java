package com.gejian.live.web.event;

import com.gejian.live.web.action.params.UnPublishActionParams;
import com.gejian.live.web.service.StreamerOfflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：lijianghuai
 * @date ：2021-11-08 15:23
 * @description：
 */

@Component
public class LiveUnPublishedEventExecutor implements LiveApplicationEventExecutor<LiveUnPublishedEvent>{

	@Autowired
	private StreamerOfflineService streamerOfflineService;

	@Override
	public void execute(LiveUnPublishedEvent liveUnPublishedEvent){
		UnPublishActionParams actionParams = (UnPublishActionParams) liveUnPublishedEvent.getActionParams();
		streamerOfflineService.StreamerEnd(Integer.parseInt(actionParams.getStream()));
	}
}
