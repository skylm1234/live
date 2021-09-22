package com.gejian.live.service;

import com.gejian.common.core.util.UCloudPictureCensorUtils;
import com.gejian.live.BaseTest;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 14:35
 * @description：
 */

@DisplayName("UCloud鉴黄测试")
public class PictureCensorServiceTest extends BaseTest {


	@Test
	public void should_success_when_call_ucloud_api_path() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("censor.png");
		double censor = UCloudPictureCensorUtils.censor(classPathResource.getFile().getAbsolutePath());
		Assertions.assertEquals(1, censor);
	}

	@Test
	public void should_success_when_call_ucloud_api_byte() throws IOException {
		InputStream resourceAsStream = PictureCensorServiceTest.class.getClassLoader().getResourceAsStream("censor.png");
		if(resourceAsStream != null){
			double censor = UCloudPictureCensorUtils.censor("censor.png", IOUtils.toByteArray(resourceAsStream));
			Assertions.assertEquals(1, censor);
		}
	}
}
