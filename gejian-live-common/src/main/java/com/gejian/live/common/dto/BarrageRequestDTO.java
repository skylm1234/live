package com.gejian.live.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ：lijianghuai
 * @date ：2021-09-22 16:17
 * @description：
 */
@Data
@ApiModel("弹幕消息请求体")
public class BarrageRequestDTO {

	@NotBlank
	@ApiModelProperty(value = "消息体",required = true)
	String content;

	@NotNull
	@ApiModelProperty(value = "房间号",required = true)
	Integer roomId;
}
