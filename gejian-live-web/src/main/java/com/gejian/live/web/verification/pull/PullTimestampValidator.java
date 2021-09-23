package com.gejian.live.web.verification.pull;


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
@Valid(type = {ValidType.PULL},order = 4)
@Slf4j
public class PullTimestampValidator extends AbstractValidator {

	@Autowired
	private TokenService tokenService;

	@Override
	public void valid(VerifyRequest request) {


		Boolean aBoolean = tokenService.judgeTimeStamp(request, ValidType.PULL);
		log.info("PullTimestampValidator valid result {}",aBoolean);



	}
}
