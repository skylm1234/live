package com.gejian.live.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *     拉流端请求dto
 * </p>
 * @author yuanxue
 * @date 2021-09-23
 */
@Data
public class PullFlowAddressDTO implements Serializable {

	/**
	 * 房间号
	 */
	@ApiModelProperty("房间号")
	@NotNull(message = "房间号不能为空")
	private Integer roomId;
}
