package com.gejian.live.web.controller.web;

import com.gejian.common.core.util.R;
import com.gejian.live.common.dto.AnchorRoomDTO;
import com.gejian.live.web.service.AnchorRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ：yuanxue
 * @date ：2021-09-22
 * @description：直播申请controller
 */
@RestController
@RequestMapping("/web/live/check")
@Api(value = "web-live-check", tags = "直播审核")
@ApiOperation(value = "直播审核")
@Slf4j
public class AnchorRoomController {
	@Autowired
	private AnchorRoomService anchorRoomService;

	@ApiOperation("主播申请审核通过接口")
	@PostMapping("/check")
	public R check(@Valid @RequestBody AnchorRoomDTO anchorRoomDTO){
		return R.ok(this.anchorRoomService.saveAnchorRoom(anchorRoomDTO));
	}

}
