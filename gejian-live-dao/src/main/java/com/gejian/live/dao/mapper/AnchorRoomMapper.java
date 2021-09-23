package com.gejian.live.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gejian.live.dao.entity.AnchorRoom;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuanxue
 * @since 2021-09-22
 */
public interface AnchorRoomMapper extends BaseMapper<AnchorRoom> {

	/**
	 * 通过主播用户id获取房间号
	 * @param userId
	 * @return
	 */
	Integer getByUserId(Long userId);
}