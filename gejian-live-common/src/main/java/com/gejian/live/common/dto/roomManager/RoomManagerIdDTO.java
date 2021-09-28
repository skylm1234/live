package com.gejian.live.common.dto.roomManager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author fengliang
 * @Date 2021/9/28
 * @description:
 */
@Data
public class RoomManagerIdDTO {

	/**
	 * 房管表id
	 */
	@ApiModelProperty(value = "房管表id", required = true)
	@NotNull(message = "房管表id不能为空")
	private Long id;
}
