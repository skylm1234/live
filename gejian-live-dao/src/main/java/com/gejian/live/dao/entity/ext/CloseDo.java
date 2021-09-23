package com.gejian.live.dao.entity.ext;

import lombok.Data;

/**
 * 客户端关闭对象
 * @author fengliang
 * @Date 2021/9/23
 * @description: on_close
 *
 */
@Data
public class CloseDo{

	private String action;
	private Integer client_id;
	private String ip;
	private String vhost;
	private String app;
	private Integer send_bytes;
	private Integer recv_bytes;



}
