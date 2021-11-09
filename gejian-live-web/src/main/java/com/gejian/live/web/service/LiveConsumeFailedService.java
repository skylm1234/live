package com.gejian.live.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.live.common.enums.LiveConsumeFailedType;
import com.gejian.live.dao.entity.LiveConsumeFailedRecord;

/**
 * @author ：lijianghuai
 * @date ：2021-09-22 16:47
 * @description：
 */
public interface LiveConsumeFailedService extends IService<LiveConsumeFailedRecord> {
	/**
	 * 保存消费失败记录
	 *
	 * @param msgBody
	 * @param exception
	 * @param msgType
	 */
	void saveRecord(String msgBody, Throwable exception, LiveConsumeFailedType msgType);

}
