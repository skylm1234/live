package com.gejian.live.web.service.impl;

import com.gejian.common.log.entity.SysLog;
import com.gejian.common.log.event.GeJianLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fengliang
 * @Date 2021/9/16
 * @description:
 */
@Slf4j
@Service
public class LogServiceImpl implements GeJianLogService {
	@Override
	public void saveLog(SysLog sysLog) {
		log.info("日志:{}", sysLog);
	}
}
