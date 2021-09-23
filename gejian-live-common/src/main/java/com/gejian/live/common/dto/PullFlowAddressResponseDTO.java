package com.gejian.live.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *     拉流端响应dto
 * </p>
 * @author yuanxue
 * @date 2021-09-23
 */
@Data
public class PullFlowAddressResponseDTO implements Serializable {

	/**
	 * rtmp地址
	 */
	@ApiModelProperty("rtmp地址")
	private String rtmpAddress;

	/**
	 * hls地址
	 */
	@ApiModelProperty("hls地址")
	private String hlsAddress;


}
