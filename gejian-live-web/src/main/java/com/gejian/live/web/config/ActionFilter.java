package com.gejian.live.web.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ：lijianghuai
 * @date ：2021-11-08 11:26
 * @description：
 */

@Slf4j
public class ActionFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		CustomHttpServletRequestWrapper customHttpServletRequestWrapper = null;
		try {
			HttpServletRequest req = (HttpServletRequest)servletRequest;
			customHttpServletRequestWrapper = new CustomHttpServletRequestWrapper(req);
		}catch (Exception e){
			log.warn("customHttpServletRequestWrapper Error:", e);
		}

		filterChain.doFilter(customHttpServletRequestWrapper, servletResponse);
	}
}
