package com.gejian.live.web.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.live.common.dto.AnchorDTO;
import com.gejian.live.dao.entity.Anchor;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuanxue
 * @since 2021-09-22
 */
public interface AnchorService extends IService<Anchor> {
	/**
	 * 保存主播申请房间信息
	 * @param anchorDTO 申请信息
	 * @return
	 */
	Boolean saveAnchor(AnchorDTO anchorDTO);

}