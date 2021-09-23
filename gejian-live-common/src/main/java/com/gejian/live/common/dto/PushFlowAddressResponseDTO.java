package com.gejian.live.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *     推流端响应dto
 * </p>
 * @author yuanxue
 * @date 2021-09-23
 */
@Data
public class PushFlowAddressResponseDTO implements Serializable {

	/**
	 * 地址
	 */
	@ApiModelProperty("地址")
	private String address;

	/**
	 * 串流码
	 */
	@ApiModelProperty("串流码")
	private String streamingCode;


}
