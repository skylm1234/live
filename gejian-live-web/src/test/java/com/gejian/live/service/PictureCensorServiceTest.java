package com.gejian.live.service;

import com.gejian.live.BaseTest;
import com.gejian.live.web.service.PictureCensorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 14:35
 * @description：
 */

@DisplayName("UCloud鉴黄测试")
public class PictureCensorServiceTest extends BaseTest {

	@Autowired
	private PictureCensorService pictureCensorService;

	@Test
	public void should_success_when_call_ucloud_api() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("censor.png");
		Assertions.assertTrue(pictureCensorService.censor(classPathResource.getFile().getAbsolutePath()));
	}
}
