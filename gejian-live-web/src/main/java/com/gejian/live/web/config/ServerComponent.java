package com.gejian.live.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 16:09
 * @description：直播服务器配置
 */
@Component
public class ServerComponent {


	@Value("${gejian.live.server.host:localhost}")
	private String host;

	@Value("${gejian.live.server.rtmp:1935}")
	private int rtmpPort;

	@Value("${gejian.live.server.http:8080}")
	private int httpPort;

	@Value("${gejian.live.server.console:1985}")
	private int consolePort;

	@Value("${gejian.live.server.stream:live}")
	private String app;

	private static final String RTMP_PREFIX = "rtmp://";

	private static final String HTTP_PREFIX = "http://";

	/**
	 * rtmp地址
	 * @return
	 */
	public String getRtmpUrl(){
		return RTMP_PREFIX + host + ":" + rtmpPort + "/" + app;
	}

	/**
	 * 带房间号的rtmp地址
	 * @param roomId
	 * @return
	 */
	public String getRtmpUrl(int roomId){
		return RTMP_PREFIX + host + ":" + rtmpPort + "/" + app + "/" + roomId;
	}

	/**
	 * m3u8播放地址
	 * @param roomId
	 * @return
	 */
	public String getm3u8Url(String roomId){
		return HTTP_PREFIX + host + ":" + httpPort + "/" + app +  "/" + roomId + "/index.m3u8";
	}

	/**
	 * 控制台地址
	 * @return
	 */
	public String getConsoleUrl(){
		return  HTTP_PREFIX + host + ":" + consolePort + "/console/";
	}

}
