package com.gejian.live.dao.entity.ext;

import lombok.Data;

/**
 * @author fengliang
 * @Date 2021/9/23
 * @description: on_hls
 */
@Data
public class HlsDo {

	private String action;
	private Integer client_id;
	private String ip;
	private String vhost;
	private String app;
	private String stream;
	private String param;
	private Integer duration;
	private String cwd;
	private String file;
	private String url;
	private String m3u8;
	private String m3u8_url;
	private Integer seq_no;


}
