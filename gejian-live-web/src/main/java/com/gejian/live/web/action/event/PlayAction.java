package com.gejian.live.web.action.event;

import com.gejian.live.common.constants.TokenConstants;
import com.gejian.live.web.action.params.ActionParams;
import com.gejian.live.web.action.params.PlayActionParams;
import com.gejian.live.web.event.LivePublishedEvent;
import com.gejian.live.web.verification.PullValidChain;
import com.gejian.live.web.verification.VerifyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author ：lijianghuai
 * @date ：2021-09-28 14:42
 * @description：
 */

@Component("on_play")
public class PlayAction implements Action {

	@Autowired
	private PullValidChain pullValidChain;

	@Autowired
	private ApplicationContext applicationContext;


	@Override
	public void execute(ActionParams actionParams) {
		PlayActionParams playActionParams = (PlayActionParams) actionParams;
		VerifyRequest verifyRequest = new VerifyRequest();
		verifyRequest.setRoomId(playActionParams.getStream());
		verifyRequest.setToken(playActionParams.getExtra().get(TokenConstants.TOKEN));
		verifyRequest.setExpireTimestamp(Long.parseLong(playActionParams.getExtra().get(TokenConstants.TIMESTAMP)));
		pullValidChain.process(verifyRequest);
		applicationContext.publishEvent(new LivePublishedEvent(this,actionParams));
	}
}
