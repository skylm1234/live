package com.gejian.live.web.controller.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.common.core.util.R;
import com.gejian.live.common.dto.gift.*;
import com.gejian.live.web.service.LiveGiftService;
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
 * @description：直播礼物后台管理api
 */
@RestController
@RequestMapping("/web/live/gift")
@Api(value = "web-live-gift", tags = "直播礼物后台管理api")
@ApiOperation(value = "直播礼物后台管理api")
@Slf4j
public class LiveGiftController {
	@Autowired
	private LiveGiftService liveGiftService;

	/**
	 * 礼物查询接口
	 * @param liveGiftPageDTO  直播间礼物列表请求dto
	 * @return IPage
	 */
	@ApiOperation("礼物查询接口")
	@PostMapping("/page")
	public R<IPage<LiveGiftResponseDTO>> queryLiveGiftList(@Valid @RequestBody LiveGiftPageDTO liveGiftPageDTO){
		return R.ok(this.liveGiftService.queryLiveGiftList(liveGiftPageDTO));
	}


	/**
	 * 直播间礼物新增接口
	 * @param liveGiftSaveDTO 直播间礼物dto
	 * @return boolean
	 */
	@ApiOperation("直播间礼物新增接口")
	@PostMapping("/save")
	public R saveLiveGift(@Valid @RequestBody LiveGiftSaveDTO liveGiftSaveDTO){
		return R.ok(this.liveGiftService.saveLiveGift(liveGiftSaveDTO));
	}

	/**
	 * 直播间礼物修改接口
	 * @param liveGiftUpdateDTO 直播间礼物修改dto
	 * @return boolean
	 */
	@ApiOperation("直播间礼物新增接口")
	@PostMapping("/update")
	public R updateLiveGift(@Valid @RequestBody LiveGiftUpdateDTO liveGiftUpdateDTO){
		return R.ok(this.liveGiftService.updateLiveGift(liveGiftUpdateDTO));
	}

	/**
	 * 直播间礼物删除接口
	 * @param liveGiftDelDTO 直播间礼物删除dto
	 * @return boolean
	 */
	@ApiOperation("直播间礼物新增接口")
	@PostMapping("/remove")
	public R removeLiveGift(@Valid @RequestBody LiveGiftDelDTO liveGiftDelDTO){
		return R.ok(this.liveGiftService.removeLiveGift(liveGiftDelDTO));
	}

}
