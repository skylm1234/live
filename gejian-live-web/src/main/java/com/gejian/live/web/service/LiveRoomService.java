package com.gejian.live.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.live.dao.entity.LiveRoom;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengliang
 * @since 2021-09-16
 */
public interface LiveRoomService extends IService<LiveRoom> {
	/**
	 * 根据版本和id修改
	 * @param one
	 * @return
	 */
	boolean updateByVersionAndId(LiveRoom one);
}
