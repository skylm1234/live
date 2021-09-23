package com.gejian.live.web.controller.app;

import com.gejian.common.core.annotation.CurrentUser;
import com.gejian.common.core.util.R;
import com.gejian.common.security.service.GeJianUser;
import com.gejian.live.common.dto.BarrageRequestDTO;
import com.gejian.live.dao.entity.LiveBulletChat;
import com.gejian.live.web.service.LiveBulletChatService;
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
 * @author ：lijianghuai
 * @date ：2021-09-22 16:20
 * @description：
 */

@Api("弹幕消息api")
@RestController
@RequestMapping("/app/barrage/")
@Slf4j
public class BarrageMessageController {

	@Autowired
	private LiveBulletChatService liveBulletChatService;

	/**
	 *  保存弹幕
	 * @param barrageRequestDTO
	 * @param geJianUser
	 * @return
	 */
	@PostMapping("/send")
	@ApiOperation("发送弹幕")
	public R<Void> send(@RequestBody @Valid BarrageRequestDTO barrageRequestDTO, @CurrentUser GeJianUser geJianUser){
		LiveBulletChat chat = new LiveBulletChat();
		chat.setContent(barrageRequestDTO.getContent());
		chat.setRoomId(barrageRequestDTO.getRoomId());
		chat.setUserId(geJianUser.getId());
		chat.setNickname(geJianUser.getNickname());
		liveBulletChatService.save(chat);
		return R.ok();
	}
}
