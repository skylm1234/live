package com.gejian.live.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.common.security.service.GeJianUser;
import com.gejian.common.security.util.SecurityUtils;
import com.gejian.live.common.dto.AnchorDTO;
import com.gejian.live.dao.entity.Anchor;
import com.gejian.live.dao.mapper.AnchorMapper;
import com.gejian.live.web.service.AnchorService;
import org.springframework.stereotype.Service;

/**
 * @author yuanxue
 * @Date 2021/9/22
 * @description: 直播申请
 */
@Service
public class AnchorServiceImpl extends ServiceImpl<AnchorMapper, Anchor> implements AnchorService {

	@Override
	public Boolean saveAnchor(AnchorDTO anchorDTO) {
		final GeJianUser user = SecurityUtils.getUser();
		Anchor anchor = BeanUtil.copyProperties(anchorDTO, Anchor.class);
		anchor.setUserId(user.getId());
		anchor.setStatus(0);
		return this.save(anchor);
	}
}