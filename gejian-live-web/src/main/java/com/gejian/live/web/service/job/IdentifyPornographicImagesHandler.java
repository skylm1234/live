package com.gejian.live.web.service.job;

import cn.hutool.core.collection.CollectionUtil;
import com.gejian.live.dao.entity.StreamerOnline;
import com.gejian.live.web.service.StreamerOnlineService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author:chen
 * @Date: 2021/9/22 11:54
 */
@Component
@Slf4j
public class IdentifyPornographicImagesHandler {

	@Autowired
	private StreamerOnlineService streamerOnlineService;

	@Autowired
	private ThreadPoolTaskExecutor convertExecutor;

	@Bean(name = "convertExecutor")
	public ThreadPoolTaskExecutor executor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 设置核心线程数
		executor.setCorePoolSize(5);
		// 设置最大线程数
		executor.setMaxPoolSize(10);
		// 设置队列容量
		executor.setQueueCapacity(20);
		// 设置线程活跃时间（秒）
		executor.setKeepAliveSeconds(60);
		// 设置默认线程名称
		executor.setThreadNamePrefix("images-job-");
		// 设置拒绝策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 等待所有任务结束后再关闭线程池
		executor.setWaitForTasksToCompleteOnShutdown(true);
		return executor;
	}

	/**
	 * 定时任务直播截图鉴定
	 * @param param
	 * @return
	 */
	@XxlJob("IdentificationImages")
	public ReturnT<String> execute(String param){

		log.info("启动直播截图鉴定定时服务");
		//查询所有
		List<StreamerOnline> list = streamerOnlineService.list();
		if (CollectionUtil.isEmpty(list)){
			XxlJobHelper.handleSuccess("目前没有主播开播，无需执行直播截图鉴定");
			return ReturnT.SUCCESS;
		}

		list.forEach(item->{
			convertExecutor.execute(()->{

			});
		});

		XxlJobHelper.handleSuccess();
		return ReturnT.SUCCESS;
	}

}
