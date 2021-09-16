package com.gejian.live.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gejian.live.dao.entity.LiveRoom;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fengliang
 * @since 2021-09-16
 */
public interface LiveRoomMapper extends BaseMapper<LiveRoom> {
	/**
	 * 根据版本号和id修改占用情况
	 * @param one
	 * @return
	 */
	int updateByVersionAndId(LiveRoom one);
}
