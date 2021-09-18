package com.gejian.live.web.verification;

import org.springframework.stereotype.Service;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 17:35
 * @description：是否开通直播权限校验
 */

@Service
@Valid(type = {ValidType.PUSH},order = 1)
public class StreamerValidator extends AbstractValidator{
	@Override
	public void valid(VerifyRequest request) {
		System.out.println("streamer valid pass...");
	}
}
