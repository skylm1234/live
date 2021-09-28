package com.gejian.live.common.enums.error;

import com.gejian.common.core.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author:chen
 * @Date: 2021/9/28 10:26
 */
@Getter
@AllArgsConstructor
public enum LiveJobErrorCode implements ErrorCode {


	NOT_FOUND_PARAM(13500, "未发现方法参数"),
	UPLOAD_PICTURE_FAILURE(13501, "直播图片上传失败");

	private final int code;

	private final String msg;
}
