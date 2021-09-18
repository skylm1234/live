package com.gejian.live;

import com.gejian.live.web.GeJianLiveWebApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 14:34
 * @description：
 */
@SpringBootTest(classes = GeJianLiveWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;
	//设置mockMvc
	@BeforeEach
	public void setMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

}
