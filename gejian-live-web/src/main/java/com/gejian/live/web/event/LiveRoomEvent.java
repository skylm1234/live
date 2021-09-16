package com.gejian.live.web.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author fengliang
 * @Date 2021/9/16
 * @description:
 */
public class LiveRoomEvent extends ApplicationEvent {

	public LiveRoomEvent(Object source) {
		super(source);
	}
}
