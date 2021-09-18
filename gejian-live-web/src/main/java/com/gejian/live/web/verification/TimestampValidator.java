package com.gejian.live.web.verification;

import org.springframework.stereotype.Service;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 17:29
 * @description：有效期校验
 */

@Service
@Valid(type = {ValidType.PUSH,ValidType.PULL})
public class TimestampValidator extends AbstractValidator{
	@Override
	public void valid(VerifyRequest request) {
		System.out.println("timestamp valid pass...");

	}
}
