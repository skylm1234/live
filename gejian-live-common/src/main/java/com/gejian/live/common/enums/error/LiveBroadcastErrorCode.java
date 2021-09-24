package com.gejian.live.common.enums.error;

import com.gejian.common.core.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fengliang
 * @Date 2021/9/23
 * @description:
 */
@Getter
@AllArgsConstructor
public enum LiveBroadcastErrorCode implements ErrorCode {

	TIMESTAMP_FAIL(1110001, "校验时间戳失败！"),
	TOKEN_FAIL(111002, "校验token失败！"),
	BROADCAST_PERMISSION_FAIL(111003, "没有直播权限！"),
	ILLEGAL_PARAMETER(111004,"客户端参数不合法！"),
	;


	private final int code;

	private final String msg;
}
