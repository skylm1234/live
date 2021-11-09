package com.gejian.live.web.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @author ：lijianghuai
 * @date ：2021-11-09 10:08
 * @description：
 */
public interface LiveApplicationEventExecutor<E extends LiveApplicaitonEvent> {

	@Async
	@EventListener
	void execute(E p);
}
