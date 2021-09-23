package com.gejian.live.web.service.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.live.common.enums.LiveConsumeFailedType;
import com.gejian.live.dao.entity.LiveConsumeFailedRecord;
import com.gejian.live.dao.mapper.LiveConsumeFailedMapper;
import com.gejian.live.web.service.LiveConsumeFailedService;
import org.springframework.stereotype.Service;

/**
 * @author ：lijianghuai
 * @date ：2021-09-22 16:47
 * @description：
 */

@Service
public class LiveConsumeFailedServiceImpl extends ServiceImpl<LiveConsumeFailedMapper,LiveConsumeFailedRecord> implements LiveConsumeFailedService {

	@Override
	public void saveRecord(String msgBody, Exception exception, LiveConsumeFailedType msgType) {
		LiveConsumeFailedRecord record = new LiveConsumeFailedRecord();
		record.setMsgBody(msgBody);
		record.setMsgType(msgType);
		String errorMessage = StrUtil.sub(ExceptionUtil.getMessage(exception), 0, 200);
		record.setErrorMessage(errorMessage);
		this.save(record);
	}

}
