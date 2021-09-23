package com.gejian.live.web.verification.pull;

import com.gejian.common.core.exception.BusinessException;
import com.gejian.live.common.enmus.error.LiveBroadcastErrorCode;
import com.gejian.live.web.service.TokenService;
import com.gejian.live.web.verification.AbstractValidator;
import com.gejian.live.web.verification.Valid;
import com.gejian.live.web.verification.ValidType;
import com.gejian.live.web.verification.VerifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 17:19
 * @description：token校验
 */

@Valid(type = {ValidType.PULL},order = 3)
@Service
@Slf4j
public class PullTokenValidator extends AbstractValidator {

	@Autowired
	private TokenService tokenService;
	@Override
	public void valid(VerifyRequest request) {

		Boolean aBoolean=tokenService.judgeToken(request,ValidType.PULL);
		log.info("PullTokenValidator valid result {}",aBoolean);
		if(!aBoolean){
			throw new BusinessException(LiveBroadcastErrorCode.TOKEN_FAIL);
		}
	}
}
