package com.gejian.live.web.service;

/**
 * @Author:chen
 * @Date: 2021/9/26 17:11
 */
public interface TaskService {

	/**
	 * 创建定时任务并执行
	 *
	 * @param roomCode
	 */
	Integer addAndStart(Integer roomCode);

	/**
	 * 删除定时任务
	 *
	 * @param jobId
	 */
	Boolean remove(Integer jobId);

}
