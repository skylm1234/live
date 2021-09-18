package com.gejian.live.api;

import com.gejian.live.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author ：lijianghuai
 * @date ：2021-09-18 10:23
 * @description：
 */

@DisplayName("校验测试测试")
public class CallBackAPITest extends BaseTest {

	@DisplayName("多线程测试推流")
	@RepeatedTest(10)
	@Execution(ExecutionMode.CONCURRENT)
	public void testPush() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/streams")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{}")
				).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}
