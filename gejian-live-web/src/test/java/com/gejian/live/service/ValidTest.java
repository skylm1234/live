package com.gejian.live.service;

import com.gejian.live.BaseTest;
import com.gejian.live.web.verification.PullValidChain;
import com.gejian.live.web.verification.PushValidChain;
import com.gejian.live.web.verification.VerifyRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：lijianghuai
 * @date ：2021-09-18 10:06
 * @description：
 */

@DisplayName("推拉流校验测试")
public class ValidTest extends BaseTest {

	@Autowired
	private PushValidChain pushValidChain;

	@Autowired
	private PullValidChain pullValidChain;

	@DisplayName("多线程测试拉流验证")
	@RepeatedTest(10)
	@Execution(ExecutionMode.CONCURRENT)
	public void testPullValid(){
		pullValidChain.process(new VerifyRequest());
	}

	@DisplayName("多线程测试推流验证")
	@RepeatedTest(10)
	@Execution(ExecutionMode.CONCURRENT)
	public void testPushValid(){
		pushValidChain.process(new VerifyRequest());
	}
}
