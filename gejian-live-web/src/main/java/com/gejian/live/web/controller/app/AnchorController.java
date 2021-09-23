package com.gejian.live.web.controller.app;

import com.gejian.common.core.util.R;
import com.gejian.live.common.dto.AnchorDTO;
import com.gejian.live.web.service.AnchorService;
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
 * @description：直播申请直播controller
 */
@RestController
@RequestMapping("/app/anchor")
@Api(value = "app-anchor", tags = "主播申请直播")
@ApiOperation(value = "主播申请直播")
@Slf4j
public class AnchorController {
	@Autowired
	private AnchorService anchorService;

	/**
	 * 主播申请接口
	 * @param anchorDTO 申请信息
	 * @return
	 */
	@ApiOperation("主播申请接口")
	@PostMapping("/apply")
	public R apply(@Valid @RequestBody AnchorDTO anchorDTO){
		return R.ok(this.anchorService.saveAnchor(anchorDTO));
	}

}
