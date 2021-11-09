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
public class CloseConnectionActionParams extends ActionParams {

	private long send_bytes;

	private long recv_bytes;

}
