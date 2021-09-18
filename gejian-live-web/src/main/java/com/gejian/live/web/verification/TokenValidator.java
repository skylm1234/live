package com.gejian.live.web.verification;

import org.springframework.stereotype.Service;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 17:19
 * @description：token校验
 */

@Valid(type = {ValidType.PUSH,ValidType.PULL})
@Service
public class TokenValidator extends AbstractValidator{

	@Override
	public void valid(VerifyRequest request) {
		System.out.println("token valid pass...");
	}
}
