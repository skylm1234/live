package com.gejian.live.dao.entity.ext;

import lombok.Data;

/**
 * @author fengliang
 * @Date 2021/9/23
 * @description: on_dvr
 */
@Data
public class DvrDo {

	private String action;
	private Integer client_id;
	private String ip;
	private String vhost;
	private String app;
	private String stream;
	private String param;
	private String cwd;
	private String file;


}
