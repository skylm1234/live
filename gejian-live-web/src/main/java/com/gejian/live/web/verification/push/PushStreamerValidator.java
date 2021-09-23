package com.gejian.live.web.verification.push;

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
 * @date ：2021-09-17 17:35
 * @description：是否开通直播权限校验
 */

@Service
@Valid(type = {ValidType.PUSH},order = 1)
@Slf4j
public class PushStreamerValidator extends AbstractValidator {


	@Autowired
	private TokenService tokenService;
	@Override
	public void valid(VerifyRequest request) {

		log.info("PushStreamerValidator valid result {}");
	}
}
