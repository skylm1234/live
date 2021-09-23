package com.gejian.live.web.verification.push;

import com.gejian.common.core.exception.BusinessException;
import com.gejian.live.common.enums.error.LiveBroadcastErrorCode;
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
 * @date ：2021-09-17 17:29
 * @description：有效期校验
 */

@Service
@Valid(type = {ValidType.PUSH},order = 4)
@Slf4j
public class PushTimestampValidator extends AbstractValidator {

	@Autowired
	private TokenService tokenService;

	@Override
	public void valid(VerifyRequest request) {
		Boolean aBoolean=tokenService.judgeTimeStamp(request,ValidType.PUSH);
		log.info("PushTimestampValidator valid resul {}",aBoolean);
		if(!aBoolean){
			throw new BusinessException(LiveBroadcastErrorCode.TIMESTAMP_FAIL);
		}
	}
}
