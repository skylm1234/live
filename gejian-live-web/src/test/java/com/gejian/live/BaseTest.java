package com.gejian.live;

import com.gejian.live.web.GeJianLiveWebApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 14:34
 * @description：
 */
@SpringBootTest(classes = GeJianLiveWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {
}
