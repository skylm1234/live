package com.gejian.live.dao.entity.ext;

import lombok.Data;

/**
 * 客户端连接对象
 * @author fengliang
 * @Date 2021/9/23
 * @description: on_connect
 */
@Data
public class ConnectDo{

	private String action;
	private Integer client_id;
	private String ip;
	private String vhost;
	private String app;
	private String tcUrl;
	private String pageUr;



}
