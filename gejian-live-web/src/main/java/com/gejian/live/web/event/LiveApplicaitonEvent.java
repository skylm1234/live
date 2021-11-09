package com.gejian.live.web.event;

import com.gejian.live.web.action.params.ActionParams;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author ：lijianghuai
 * @date ：2021-11-08 16:07
 * @description：
 */
@Getter
public abstract class LiveApplicaitonEvent extends ApplicationEvent {
	protected ActionParams actionParams;
	public LiveApplicaitonEvent(Object source,ActionParams actionParams) {
		super(source);
		this.actionParams = actionParams;
	}
}
