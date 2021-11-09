package com.gejian.live.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 11:19
 * @description：
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private LiveActionInterceptor liveActionInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(liveActionInterceptor).addPathPatterns("/callback/api/v1/**");
	}

	@Bean
	public FilterRegistrationBean<ActionFilter> servletRegistrationBean() {
		ActionFilter actionFilter = new ActionFilter();
		FilterRegistrationBean<ActionFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(actionFilter);
		bean.setName("actionFilter");
		bean.addUrlPatterns("/callback/api/v1/*");
		bean.setOrder(Ordered.LOWEST_PRECEDENCE);
		return bean;
	}
}
