package com.gejian.live.web.controller.web;

import com.gejian.live.common.dto.watch.UserWatchOnlineSaveDTO;
import com.gejian.live.dao.entity.UserWatchOnline;
import com.gejian.live.web.service.StreamerOfflineService;
import com.gejian.live.web.service.StreamerOnlineService;
import cn.hutool.json.JSONUtil;
import com.gejian.common.core.exception.BusinessException;
import com.gejian.live.common.constants.TokenConstants;
import com.gejian.live.common.enums.error.LiveBroadcastErrorCode;
import com.gejian.live.dao.entity.ext.PublishDo;
import com.gejian.live.web.service.UserWatchOfflineService;
import com.gejian.live.web.service.UserWatchOnlineService;
import com.gejian.live.web.verification.PullValidChain;
import com.gejian.live.web.verification.PushValidChain;
import com.gejian.live.web.verification.VerifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

	@Autowired
	private PushValidChain pushValidChain;

	@Autowired
	private PullValidChain pullValidChain;

	@Autowired
	private StreamerOnlineService streamerOnlineService;
	@Autowired
	private StreamerOfflineService streamerOfflineService;
	@Autowired
	private UserWatchOnlineService userWatchOnlineService;
	@Autowired
	private UserWatchOfflineService userWatchOfflineService;


	private List<String> ipIgnoreList = Arrays.asList("127.0.0.1");

	@RequestMapping(value = "/clients", method = RequestMethod.POST)
	public int clients(@RequestBody String bodyString) {
		log.info("# 触发事件 [ 客户端连接 ], msg = {}", bodyString);
		return 0;
	}

	@RequestMapping(value = "/closeClients", method = RequestMethod.POST)
	public int closeClients(@RequestBody String bodyString) {
		log.info("# 触发事件 [ 客户端连接关闭 ], msg = {}", bodyString);
		return 0;
	}

	@RequestMapping(value = "/streams", method = RequestMethod.POST)
	public int streams(@RequestBody String bodyString) {
		PublishDo publishDo = JSONUtil.toBean(bodyString, PublishDo.class);
		if (!ipIgnoreList.contains(publishDo.getIp())) {
			try {
				VerifyRequest verifyRequest = buildVerifyRequest(publishDo);
				log.info("# 触发事件 [ 客户端发布流 ], msg = {} , push-publishDo={}", bodyString, publishDo);
				pushValidChain.process(verifyRequest);
			} catch (Exception e) {
				log.error("push-客户端不合法", e);
				return -1;
			}
		}
		//TODO 主播开播
		streamerOnlineService.StreamerStart(null, null, null, null);

		log.info("# 触发事件 [ 客户端发布流 ], msg = {}", bodyString);
		return 0;
	}

	@RequestMapping(value = "/closeStreams", method = RequestMethod.POST)
	public int closeStreams(@RequestBody String bodyString) {
		pushValidChain.process(new VerifyRequest());
		log.info("# 触发事件 [ 客户端停止发布流 ], msg = {}", bodyString);

		//TODO 主播结束直播
		streamerOfflineService.StreamerEnd(null);

		return 0;
	}


	@RequestMapping(value = "/sessions", method = RequestMethod.POST)
	public int sessions(@RequestBody String bodyString) {
		PublishDo publishDo = JSONUtil.toBean(bodyString, PublishDo.class);
		if (!ipIgnoreList.contains(publishDo.getIp())) {
			try {
				VerifyRequest verifyRequest = buildVerifyRequest(publishDo);
				log.info("# 触发事件 [ 播放流 ], msg = {} , play-publishDo={}", bodyString, publishDo);
				pullValidChain.process(verifyRequest);
			} catch (Exception e) {
				log.error("play-客户端不合法", e);
				return -1;
			}

		}
		//TODO 用户播放流的时候记录用户基本信息
		userWatchOnlineService.saveUserWatchOnline(new UserWatchOnlineSaveDTO());

		return 0;
	}


	@RequestMapping(value = "/onStop", method = RequestMethod.POST)
	public int onStop(@RequestBody String bodyString) {
		//当客户端停止播放时。备注：停止播放可能不会关闭连接，还能再继续播放
		log.info("# 触发事件 [ 客户端停止播放 ], msg = {}", bodyString);

		//TODO 用户停止播放流的时候记录用户基本信息
		userWatchOfflineService.saveUserWatchOffline(null, null);
		return 0;
	}

	@RequestMapping(value = "/dvrs", method = RequestMethod.POST)
	public int dvrs(@RequestBody String bodyString) {
		log.info("# 触发事件 [ dvrs ], msg = {}", bodyString);
		return 0;
	}

	@RequestMapping(value = "/hls", method = RequestMethod.POST)
	public int hls(@RequestBody String bodyString) {
		log.info("# 触发事件 [ hls ], msg = {}", bodyString);
		return 0;
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
		log.info("# 触发事件 [ hls ], app= {}, stream = {}, ts_url = {}, param= {}", app, stream, ts_url, param);
		return 0;
	}

	/**
	 * 解析param  ?token=123&expire=132
	 *
	 * @param param
	 * @return
	 */
	public Map<String, String> resolveParam(String param) {
		Map<String, String> hashMap = new HashMap<>();
		String substring = param.substring(1);
		String[] split = substring.split("&");
		for (String one : split) {
			String[] obj = one.split("=");
			hashMap.put(obj[0], obj[1]);
		}
		return hashMap;
	}


	/**
	 * 构造verifyRequest
	 *
	 * @param publishDo
	 * @return
	 */
	private VerifyRequest buildVerifyRequest(PublishDo publishDo) {
		VerifyRequest verifyRequest = new VerifyRequest();
		try {
			Map<String, String> map = resolveParam(publishDo.getParam());
//			verifyRequest.setUserId(0);
			verifyRequest.setRoomId(publishDo.getStream());
			verifyRequest.setToken(map.get(TokenConstants.TOKEN));
			verifyRequest.setExpireTimestamp(Long.valueOf(map.get(TokenConstants.EXPIRETIMESTAMP)));
		} catch (Exception e) {
			log.error("客户端参数不合法", e);
			throw new BusinessException(LiveBroadcastErrorCode.ILLEGAL_PARAMETER);
		}

		return verifyRequest;
	}
}
