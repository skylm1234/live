package com.gejian.live.web.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.live.common.dto.AnchorRoomDTO;
import com.gejian.live.dao.entity.AnchorRoom;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yuanxue
 * @since 2021-09-22
 */
public interface AnchorRoomService extends IService<AnchorRoom> {

	/**
	 * 保存主播房间信息
	 *
	 * @param anchorRoomDTO 申请信息
	 * @return
	 */
	Boolean saveAnchorRoom(AnchorRoomDTO anchorRoomDTO);

	Boolean changeLiveStatus(Long userId, boolean roomLive);
}