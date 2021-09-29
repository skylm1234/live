package com.gejian.live.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.common.core.exception.BusinessException;
import com.gejian.live.common.dto.gift.*;
import com.gejian.live.common.enums.error.LiveGiftErrorCode;
import com.gejian.live.dao.entity.LiveGift;
import com.gejian.live.dao.mapper.LiveGiftMapper;
import com.gejian.live.web.service.LiveGiftService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author yuanxue
 * @since 2021-09-28
 */
@Service
public class LiveGiftServiceImpl extends ServiceImpl<LiveGiftMapper, LiveGift> implements LiveGiftService {


	@Override
	public IPage<LiveGiftResponseDTO> queryLiveGiftList(LiveGiftPageDTO liveGiftPageDTO) {
		LambdaQueryWrapper<LiveGift> wrapper = Wrappers.<LiveGift>lambdaQuery()
				.eq(LiveGift::getDeleted, false);
		if (StrUtil.isNotBlank(liveGiftPageDTO.getName())) {
			wrapper.like(LiveGift::getName, liveGiftPageDTO.getName());
		}
		if (StrUtil.isNotBlank(liveGiftPageDTO.getType())) {
			wrapper.eq(LiveGift::getType, liveGiftPageDTO.getType());
		}
		Page<LiveGift> page = baseMapper.selectPage(liveGiftPageDTO.getPage(), wrapper);
		if (CollectionUtils.isEmpty(page.getRecords())){
			return new Page<>();
		}
		return page.convert(liveGift -> BeanUtil.copyProperties(liveGift, LiveGiftResponseDTO.class));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveLiveGift(LiveGiftSaveDTO liveGiftSaveDTO) {
		LiveGift liveGift = BeanUtil.copyProperties(liveGiftSaveDTO, LiveGift.class);
		return Optional.ofNullable(this.save(liveGift)).orElseThrow(() ->  new BusinessException(LiveGiftErrorCode.NAME_REPEAT_FAIL));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateLiveGift(LiveGiftUpdateDTO liveGiftUpdateDTO) {
		LiveGift liveGift = BeanUtil.copyProperties(liveGiftUpdateDTO, LiveGift.class);
		return Optional.ofNullable(this.updateById(liveGift)).orElseThrow(() -> new BusinessException(LiveGiftErrorCode.UPDATE_FAIL));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeLiveGift(LiveGiftDelDTO liveGiftDelDTO) {
		LiveGift liveGift = new LiveGift();
		liveGift.setDeleted(true);
		return this.update(liveGift, Wrappers.<LiveGift>lambdaUpdate().in(LiveGift::getId, liveGiftDelDTO.getIds()));
	}


}