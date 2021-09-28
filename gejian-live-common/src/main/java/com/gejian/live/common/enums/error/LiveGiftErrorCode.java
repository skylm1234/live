package com.gejian.live.common.enums.error;

import com.gejian.common.core.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *
 * </p>
 * @author yuanxue
 * @date 2021-09-28
 */
@Getter
@AllArgsConstructor
public enum LiveGiftErrorCode implements ErrorCode {

	/**
	 *
	 */
	NAME_REPEAT_FAIL(110004, "礼物名已存在！"),
	UPDATE_FAIL(110005, "礼物修改失败")
	;


	private final int code;

	private final String msg;
}
