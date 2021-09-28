package com.gejian.live.common.dto.roomManager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author fengliang
 * @Date 2021/9/28
 * @description:
 */
@Data
public class RoomManagerDTO {

	/**
	 * 房管id
	 */
	@ApiModelProperty(value = "房管id", required = true)
	@NotNull(message = "房管id不能为空")
	private Long managerId;

	/**
	 * 房管昵称
	 */
	@ApiModelProperty(value = "房管昵称", required = true)
	@NotBlank(message = "房管昵称不能为空")
	private String managerName;
}
