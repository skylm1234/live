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
public class AnchorRoomDTO implements Serializable {

	/**
	 * 主播申请id
	 */
	@ApiModelProperty("主播申请id")
	@NotNull(message = "主播申请id不能为空")
	private Long anchorId;


}
