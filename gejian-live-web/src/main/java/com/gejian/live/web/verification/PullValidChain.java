package com.gejian.live.web.verification;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 17:04
 * @description：
 */
@Component
public class PullValidChain extends AbstractValidChain {

	@PostConstruct
	private void init(){
		setValidator(ValidType.PULL);
	}
}
