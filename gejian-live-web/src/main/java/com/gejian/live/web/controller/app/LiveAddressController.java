package com.gejian.live.web.controller.app;

import com.gejian.common.core.util.R;
import com.gejian.live.common.dto.PushFlowAddressResponseDTO;
import com.gejian.live.common.dto.PullFlowAddressDTO;
import com.gejian.live.common.dto.PullFlowAddressResponseDTO;
import com.gejian.live.web.service.LiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ：yuanxue
 * @date ：2021-09-22
 * @description：直播推拉流地址controller
 */
@RestController
@RequestMapping("/app/live/address")
@Api(value = "app-live-address", tags = "直播推拉流地址")
@ApiOperation(value = "直播推拉流地址")
@Slf4j
public class LiveAddressController {
	@Autowired
	private LiveAddressService liveAddressService;

	/**
	 * 获取推流端地址
	 * @return
	 */
	@ApiOperation("获取推流端地址")
	@GetMapping("/push")
	public R<PushFlowAddressResponseDTO> getPushFlowAddress(){
		return R.ok(this.liveAddressService.getPushFlowAddress());
	}

	/**
	 * 获取拉流端地址
	 * @param pullFlowAddressDTO
	 * @return
	 */
	@ApiOperation("获取拉流端地址")
	@GetMapping("/pull")
	public R<PullFlowAddressResponseDTO> getPullFlowAddress(@Valid @RequestBody PullFlowAddressDTO pullFlowAddressDTO){
		return R.ok(this.liveAddressService.getPullFlowAddress(pullFlowAddressDTO));
	}

}
