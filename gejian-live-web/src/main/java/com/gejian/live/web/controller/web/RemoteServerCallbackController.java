package com.gejian.live.web.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 15:24
 * @description：直播服务器回调controller
 */
@RestController
@RequestMapping("/callback/api/v1")
@Slf4j
public class RemoteServerCallbackController {

	private static final int OK = 0;

	@RequestMapping(value = "/clients", method = RequestMethod.POST)
	public int clients() {
		return OK;
	}
	@RequestMapping(value = "/closeClients", method = RequestMethod.POST)
	public int closeClients() {
		return OK;
	}

	@RequestMapping(value = "/streams", method = RequestMethod.POST)
	public int streams() {
		return OK;
	}

	@RequestMapping(value = "/closeStreams", method = RequestMethod.POST)
	public int closeStreams() {
		return OK;
	}


	@RequestMapping(value = "/sessions", method = RequestMethod.POST)
	public int sessions() {
		return OK;
	}

	@RequestMapping(value = "/onStop", method = RequestMethod.POST)
	public int onStop() {
		return OK;
	}

	@RequestMapping(value = "/dvrs", method = RequestMethod.POST)
	public int dvrs(@RequestBody String bodyString) {
		return OK;
	}

	@RequestMapping(value = "/hls", method = RequestMethod.POST)
	public int hls() {
		return OK;
	}

	/**
	 * 类似这样: /api/v1/hls/[app]/[stream]/[ts_url][param];
	 *
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
		return OK;
	}
}
