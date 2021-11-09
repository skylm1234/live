package com.gejian.live.web.action.event;

import com.gejian.live.web.action.params.ActionParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：lijianghuai
 * @date ：2021-09-28 14:29
 * @description：
 */

public interface Action {
	Logger LOGGER = LoggerFactory.getLogger(Action.class);

	/**
	 * action 执行
	 */
	default void execute(ActionParams actionParams){
		LOGGER.info("default execute,do nothing...,actionParams = {}",actionParams);
	}
}
