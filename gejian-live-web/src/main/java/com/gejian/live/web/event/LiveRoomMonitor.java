package com.gejian.live.web.event;

import com.gejian.live.web.service.LiveRoomHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author fengliang
 * @Date 2021/9/16
 * @description:
 */
@Component
public class LiveRoomMonitor {

	@Autowired
	private LiveRoomHelper liveRoomHelper;

	@EventListener
	public void run(LiveRoomEvent liveRoomEvent){

		liveRoomHelper.compensate();
	}


}
