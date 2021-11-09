package com.gejian.live.job;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.gejian.live.web.LiveWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:chen
 * @Date: 2021/9/26 10:24
 */
@SpringBootTest(classes = LiveWebApplication.class)
@Slf4j
public class JobTest {

	@Test
	public void test() {

//		for (int i = 0; i < 10; i++) {
//			Map<String, Integer> map = new HashMap<>();
//			map.put("roomId", RandomUtil.randomInt());
//
//			String path = "https://job.gejiantech.com/xxl-job-admin" + "/jobinfo/add";
//			Map<String, Object> paramMap = new HashMap<>();
//			paramMap.put("jobGroup", 6);
//			paramMap.put("jobDesc", "这是测试任务");
//			paramMap.put("author", "admin");
//			paramMap.put("alarmEmail", "");
//			paramMap.put("scheduleType", "CRON");
//			paramMap.put("scheduleConf", "0/1 5/1 * * * ?");
//			paramMap.put("cronGen_display", "0/1 5/1 * * * ?");
//			paramMap.put("schedule_conf_CRON", "0/1 5/1 * * * ?");
//			paramMap.put("schedule_conf_FIX_RATE", "");
//			paramMap.put("schedule_conf_FIX_DELAY", "");
//			paramMap.put("glueType", "BEAN");
//			paramMap.put("executorHandler", "testHandler");
//			paramMap.put("executorParam", JSON.toJSON(map));
//			paramMap.put("executorRouteStrategy", "FIRST");
//			paramMap.put("childJobId", "");
//			paramMap.put("misfireStrategy", "DO_NOTHING");
//			paramMap.put("executorBlockStrategy", "SERIAL_EXECUTION");
//			paramMap.put("executorTimeout", 0);
//			paramMap.put("executorFailRetryCount", 0);
//			paramMap.put("glueRemark", "GLUE代码初始化");
//			paramMap.put("glueSource", "");
//
//
//			HttpResponse response = HttpRequest.post(path).form(paramMap).cookie("XXL_JOB_LOGIN_IDENTITY=7b226964223a312c22757365726e616d65223a2261646d696e222c2270617373776f7264223a223864613161663634373466333736616265616363333135616439656436323032222c22726f6c65223a312c227065726d697373696f6e223a6e756c6c7d").execute();
//
//			JSONObject jsonObject = JSON.parseObject(response.body());
//			int jobId = jsonObject.getIntValue("content");
//
//			Map<String, Object> newParam = new HashMap<>();
//			newParam.put("id",jobId);
//			HttpRequest.post(path).cookie("XXL_JOB_LOGIN_IDENTITY=7b226964223a312c22757365726e616d65223a2261646d696e222c2270617373776f7264223a223864613161663634373466333736616265616363333135616439656436323032222c22726f6c65223a312c227065726d697373696f6e223a6e756c6c7d").execute();
//			System.out.println(jobId);
//		}	

		int[] ints = {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
		for (int anInt : ints) {
			String path = "https://job.gejiantech.com/xxl-job-admin" + "/jobinfo/stop";
			Map<String, Object> newParam = new HashMap<>();
			newParam.put("id", anInt);
			HttpRequest.post(path).form(newParam).cookie("XXL_JOB_LOGIN_IDENTITY=7b226964223a312c22757365726e616d65223a2261646d696e222c2270617373776f7264223a223864613161663634373466333736616265616363333135616439656436323032222c22726f6c65223a312c227065726d697373696f6e223a6e756c6c7d").execute();
		}
	}



	public String getCookie() {
		String path = "https://job.gejiantech.com/xxl-job-admin" + "/login";
		Map<String, Object> hashMap = new HashMap();
		hashMap.put("userName", "admin");
		hashMap.put("password", "4pmFEuON");
		HttpResponse response = HttpRequest.post(path).form(hashMap).execute();

		List<HttpCookie> cookies = response.getCookies();
		StringBuilder sb = new StringBuilder();
		for (HttpCookie cookie : cookies) {
			sb.append(cookie.toString());
		}
		return sb.toString();
	}
}
