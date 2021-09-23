package com.gejian.live.common.enums.error;

import com.gejian.common.core.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ljb
 * @date 2021年08月19日 15:27
 * @description 视频相关操作错误枚举
 */
@Getter
@AllArgsConstructor
public enum LiveRoomErrorCode implements ErrorCode {

	ROOM_REPEAT_FAIL(110000, "房间号重复！"),
	ROOM_FULL_FAIL(110000, "没有多余的房间号了！"),

	;


	private final int code;

	private final String msg;
}
