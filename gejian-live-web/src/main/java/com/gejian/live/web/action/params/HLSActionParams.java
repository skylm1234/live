package com.gejian.live.web.action.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：lijianghuai
 * @date ：2021-09-28 14:29
 * @description：
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class HLSActionParams extends ActionParams {
	private Double duration;
	private String cwd;
	private String file;
	private String url;
	private String m3u8;
	private String m3u8_url;
	private Integer seq_no;

}
