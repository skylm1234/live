package com.gejian.live.web.service.impl;


import com.gejian.common.core.constant.SecurityConstants;
import com.gejian.common.core.util.R;
import com.gejian.live.web.service.TaskService;
import com.gejian.xxl.job.client.dto.XxlJobInfoDTO;
import com.gejian.xxl.job.client.dto.XxlJobInfoResponseDTO;
import com.gejian.xxl.job.client.dto.XxlJobRemoveDTO;
import com.gejian.xxl.job.client.feign.RemoteXxlJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:chen
 * @Date: 2021/9/26 17:13
 */
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

	@Autowired
	private RemoteXxlJobService remoteXxlJobService;

	@Override
	public Integer addAndStart(Integer roomId) {
		XxlJobInfoDTO xxlJobInfo = new XxlJobInfoDTO();
		//执行器ID
		xxlJobInfo.setJobGroup(6);
		//任务描述
		xxlJobInfo.setJobDesc("直播房间:" + roomId + "的鉴黄定时任务");
		//负责人
		xxlJobInfo.setAuthor("admin");
		//调度类型 固定值
		xxlJobInfo.setScheduleType("FIX_RATE");
		//调度配置，值含义取决于调度类型
		xxlJobInfo.setScheduleConf("60");
		//运行模式
		xxlJobInfo.setGlueType("BEAN");
		//JobHandler
		xxlJobInfo.setExecutorHandler("testHandler");
		//执行参数
		xxlJobInfo.setExecutorParam(String.valueOf(roomId));
		//执行器路由策略
		xxlJobInfo.setExecutorRouteStrategy("FIRST");
		//调度过期策略
		xxlJobInfo.setMisfireStrategy("DO_NOTHING");
		//阻塞处理策略
		xxlJobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
		//任务执行超时时间，单位秒
		xxlJobInfo.setExecutorTimeout(30);
		//超时重试次数
		xxlJobInfo.setExecutorFailRetryCount(3);
		//GLUE备注
		xxlJobInfo.setGlueRemark("GLUE代码初始化");

		//新建并启动一个定时任务
		R<XxlJobInfoResponseDTO> response = remoteXxlJobService.addAndStart(xxlJobInfo, SecurityConstants.FROM_IN);
		if (response.getCode() == 200) {
			int jobId = response.getData().getJobId();
			log.info("直播房间{}定时器启动成功，任务ID{}", roomId, jobId);
			return jobId;
		} else {
			log.info("直播房间{}定时器启动失败:{}", roomId, response.getMsg());
		}
		return null;
	}

	@Override
	public Boolean remove(Integer jobId) {
		XxlJobRemoveDTO removeDTO = new XxlJobRemoveDTO();
		removeDTO.setJobId(jobId);
		R<Boolean> response = remoteXxlJobService.remove(removeDTO, SecurityConstants.FROM_IN);
		if (response.getCode() == 200 && response.getData()) {
			log.info("定时任务{}删除成功", jobId);
			return true;
		} else {
			log.info("定时任务{}删除失败", jobId);
			return false;
		}
	}
}
