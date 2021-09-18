package com.gejian.live.web.verification;

import org.springframework.stereotype.Service;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 17:32
 * @description：违规校验
 */

@Service
@Valid(type = {ValidType.PUSH},order = 2)
public class ViolationValidator extends AbstractValidator{
	@Override
	public void valid(VerifyRequest request) {
		System.out.println("violation valid pass...");

	}
}
