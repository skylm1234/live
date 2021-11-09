package com.gejian.live.web.action.event;

import com.gejian.live.web.action.params.ActionParams;
import com.gejian.live.web.event.LiveUnPublishedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author ：lijianghuai
 * @date ：2021-09-28 14:41
 * @description：
 */

@Component("on_unpublish")
public class UnPublishAction implements Action {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void execute(ActionParams actionParams) {
		applicationContext.publishEvent(new LiveUnPublishedEvent(this,actionParams));
	}
}
