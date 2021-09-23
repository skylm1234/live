package com.gejian.live.web.service;

import com.gejian.live.common.dto.PullFlowAddressDTO;
import com.gejian.live.common.dto.PullFlowAddressResponseDTO;
import com.gejian.live.common.dto.PushFlowAddressResponseDTO;

/**
 * @author ：yuanxue
 * @date ：2021-09-23
 * @description：直播地址
 */
public interface LiveAddressService {

	/**
	 * 获取推流端地址
	 * @return
	 */
	PushFlowAddressResponseDTO getPushFlowAddress();

	/**
	 * 获取拉流端地址
	 * @return
	 */
	PullFlowAddressResponseDTO getPullFlowAddress(PullFlowAddressDTO pullFlowAddressDTO);

}
