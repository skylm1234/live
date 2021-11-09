package com.gejian.live.web.controller.app;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gejian.common.core.annotation.CurrentUser;
import com.gejian.common.security.service.GeJianUser;
import com.gejian.live.common.dto.roomManager.AnchorRoomManagerDTO;
import com.gejian.live.common.dto.roomManager.RoomManagerDTO;
import com.gejian.live.common.dto.roomManager.RoomManagerIdDTO;
import com.gejian.live.dao.entity.AnchorRoomManager;
import com.gejian.live.web.service.AnchorRoomManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 房管表 前端控制器
 * </p>
 *
 * @author fengliang
 * @since 2021-09-28
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/app/anchor-room-manager")
@Api(value = "anchor-room-manager-app", tags = "房管模块-app")
public class AnchorRoomManagerAppController {


	@Autowired
	private AnchorRoomManagerService anchorRoomManagerService;

	/**
	 * 给主播增加一个房管
	 * @param roomManagerDTO
	 * @param geJianUser
	 * @return
	 */
	@PostMapping("save")
	@ApiOperation("增加一个房管")
	public Boolean save(@RequestBody @Valid RoomManagerDTO roomManagerDTO, @CurrentUser GeJianUser geJianUser){
		AnchorRoomManager anchorRoomManager = new AnchorRoomManager();
		anchorRoomManager.setAnchorId(geJianUser.getId());
		anchorRoomManager.setManagerId(roomManagerDTO.getManagerId());
		anchorRoomManager.setManagerName(roomManagerDTO.getManagerName());
		return this.anchorRoomManagerService.save(anchorRoomManager);
	}



	/**
	 * 给主播增加一个房管
	 * @param roomManagerIdDTO
	 * @param geJianUser
	 * @return
	 */
	@PostMapping("delete")
	@ApiOperation("删除一个房管")
	public Boolean delete(@RequestBody @Valid RoomManagerIdDTO roomManagerIdDTO, @CurrentUser GeJianUser geJianUser){
		AnchorRoomManager anchorRoomManager = new AnchorRoomManager();
		anchorRoomManager.setDeleted(true);
		return this.anchorRoomManagerService.update(anchorRoomManager,Wrappers.lambdaQuery(AnchorRoomManager.class)
				.eq(AnchorRoomManager::getAnchorId,geJianUser.getId())
				.eq(AnchorRoomManager::getId,roomManagerIdDTO.getId())
		);
	}




	/**
	 * 查询主播所有房管信息
	 * @param geJianUser
	 * @return
	 */
	@PostMapping("query")
	@ApiOperation("查询当前主播所有房管信息")
	public List<AnchorRoomManagerDTO> query(@CurrentUser GeJianUser geJianUser){
		List<AnchorRoomManager> list = this.anchorRoomManagerService.list(Wrappers.lambdaQuery(AnchorRoomManager.class)
				.eq(AnchorRoomManager::getDeleted, false)
				.eq(AnchorRoomManager::getAnchorId, geJianUser.getId())
		);
		List<AnchorRoomManagerDTO> anchorRoomManagerDTOS = BeanUtil.copyToList(list, AnchorRoomManagerDTO.class);
		return anchorRoomManagerDTOS;
	}




}
