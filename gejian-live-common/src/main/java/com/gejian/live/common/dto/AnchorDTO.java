package com.gejian.live.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 * @author yuanxue
 * @date 2021-09-22
 */
@Data
public class AnchorDTO implements Serializable {

	/**
	 * 房间描述
	 */
	@ApiModelProperty("房间描述")
	@NotNull(message = "房间描述不能为空")
	private String roomDescription;

	/**
	 * 房间标题
	 */
	@ApiModelProperty("房间标题")
	@NotNull(message = "房间标题不能为空")
	private String roomTitle;


}
