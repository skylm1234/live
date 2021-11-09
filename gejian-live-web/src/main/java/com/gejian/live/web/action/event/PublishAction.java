package com.gejian.live.web.action.event;

import com.gejian.live.common.constants.TokenConstants;
import com.gejian.live.web.action.params.ActionParams;
import com.gejian.live.web.action.params.PublishActionParams;
import com.gejian.live.web.event.LivePublishedEvent;
import com.gejian.live.web.verification.PushValidChain;
import com.gejian.live.web.verification.VerifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author ：lijianghuai
 * @date ：2021-09-28 14:41
 * @description：
 */

@Component("on_publish")
@Slf4j
public class PublishAction implements Action {

	@Autowired
	private PushValidChain pushValidChain;

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void execute(ActionParams actionParams) {
		PublishActionParams publishActionParams = (PublishActionParams) actionParams;
		VerifyRequest verifyRequest = new VerifyRequest();
		verifyRequest.setRoomId(publishActionParams.getStream());
		verifyRequest.setToken(publishActionParams.getExtra().get(TokenConstants.TOKEN));
		verifyRequest.setExpireTimestamp(Long.parseLong(publishActionParams.getExtra().get(TokenConstants.TIMESTAMP)));
		pushValidChain.process(verifyRequest);
		applicationContext.publishEvent(new LivePublishedEvent(this,actionParams));
	}
}
