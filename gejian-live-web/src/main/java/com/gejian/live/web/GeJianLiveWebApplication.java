package com.gejian.live.web;

import com.gejian.common.feign.annotation.EnablePigFeignClients;
import com.gejian.common.job.annotation.EnablePigXxlJob;
import com.gejian.common.security.annotation.EnableGeJianResourceServer;
import com.gejian.common.swagger.annotation.EnablePigSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author
 *
 */
@EnablePigSwagger2
@EnableGeJianResourceServer
@EnablePigFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.gejian.live.dao.mapper")
public class GeJianLiveWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeJianLiveWebApplication.class, args);
	}

}
