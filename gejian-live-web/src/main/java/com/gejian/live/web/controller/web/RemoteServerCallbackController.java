package com.gejian.live.web.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 15:24
 * @description：直播服务器回调controller
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class RemoteServerCallbackController {

	@RequestMapping(value = "/clients", method = RequestMethod.POST)
	public int clients(@RequestBody String bodyString) {
		log.info("# 触发事件 [ 客户端连接 ], msg = {}", bodyString);
		return 0;
	}

	@RequestMapping(value = "/streams", method = RequestMethod.POST)
	public int streams(@RequestBody String bodyString) {
		log.info("# 触发事件 [ 客户端发布流 ], msg = {}", bodyString);
		return 0;
	}

	@RequestMapping(value = "/sessions", method = RequestMethod.POST)
	public int sessions(@RequestBody String bodyString) {
		log.info("# 触发事件 [ 播放流 ], msg = {}", bodyString);
		return 0;
	}

	@RequestMapping(value = "/dvrs", method = RequestMethod.POST)
	public int dvrs(@RequestBody String bodyString) {
		log.info( "# 触发事件 [ dvrs ], msg = {}", bodyString);
		return 0;
	}

	@RequestMapping(value = "/hls", method = RequestMethod.POST)
	public int hls(@RequestBody String bodyString) {
		log.info("# 触发事件 [ hls ], msg = {}", bodyString);
		return 0;
	}

	/**
	 * 类似这样: /api/v1/hls/[app]/[stream]/[ts_url][param];
	 * @param app
	 * @param stream
	 * @param ts_url
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/hls/{app}/{stream}/{ts_url}", method = RequestMethod.GET)
	public int on_hls_notify(@PathVariable String app,
							 @PathVariable String stream,
							 @PathVariable String ts_url,
							 @RequestParam Map<String, Object> param) {
		log.info("# 触发事件 [ hls ], app= {}, stream = {}, ts_url = {}, param= {}", app, stream, ts_url, param);
		return 0;
	}
}
