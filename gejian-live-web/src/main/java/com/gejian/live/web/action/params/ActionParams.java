package com.gejian.live.web.action.params;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * @author ：lijianghuai
 * @date ：2021-09-28 14:29
 * @description：
 */

@Data
@ToString
public abstract class ActionParams {

	protected String action;

	protected String client_id;

	protected String ip;

	protected String vhost;

	protected String app;

	protected String tcUrl;

	protected String stream;

	protected String pageUrl;

	protected String server_id;

	protected Map<String,String> extra;
}
