package com.gejian.live.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.live.common.dto.gift.*;
import com.gejian.live.dao.entity.LiveGift;

/**
 * <p>
 *
 * </p>
 *
 * @author yuanxue
 * @since 2021-09-28
 */
public interface LiveGiftService extends IService<LiveGift> {

	/**
	 * 分页查询直播间礼物列表
	 * @param liveGiftPageDTO 直播间礼物请求dto
	 * @return list
	 */
	IPage<LiveGiftResponseDTO> queryLiveGiftList(LiveGiftPageDTO liveGiftPageDTO);

	/**
	 * 直播间礼物新增
	 * @param liveGiftSaveDTO 直播间礼物dto
	 * @return boolean
	 */
	Boolean saveLiveGift(LiveGiftSaveDTO liveGiftSaveDTO);

	/**
	 * 直播间礼物修改
	 * @param liveGiftUpdateDTO 直播间礼物dto
	 * @return boolean
	 */
	Boolean updateLiveGift(LiveGiftUpdateDTO liveGiftUpdateDTO);

	/**
	 * 直播间礼物删除
	 * @param liveGiftDelDTO 直播间礼物dto
	 * @return boolean
	 */
	Boolean removeLiveGift(LiveGiftDelDTO liveGiftDelDTO);
}