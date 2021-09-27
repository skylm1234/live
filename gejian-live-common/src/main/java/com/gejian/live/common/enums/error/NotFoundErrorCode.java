package com.gejian.live.common.enums.error;

import com.gejian.common.core.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：lijianghuai
 * @date ：2021-09-27 11:57
 * @description：
 */

@Getter
@AllArgsConstructor
public enum NotFoundErrorCode implements ErrorCode {
	/**
	 * 不存在
	 */
	INSTANCE(404, "资源不存在！");

	private final int code;

	private final String msg;

}
