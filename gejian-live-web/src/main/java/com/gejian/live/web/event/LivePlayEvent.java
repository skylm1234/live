package com.gejian.live.web.event;

import com.gejian.live.web.action.params.ActionParams;
import lombok.Getter;

/**
 * @author ：lijianghuai
 * @date ：2021-11-08 15:15
 * @description：
 */

@Getter
public class LivePlayEvent extends LiveApplicaitonEvent {

	public LivePlayEvent(Object source, ActionParams actionParams) {
		super(source, actionParams);
	}
}
