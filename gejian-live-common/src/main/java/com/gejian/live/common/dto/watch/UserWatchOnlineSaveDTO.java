package com.gejian.live.common.dto.watch;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author:chen
 * @Date: 2021/9/24 13:45
 */
@Data
@ApiModel("用户直播线上观看记录")
public class UserWatchOnlineSaveDTO {


	/**
	 * 观看直播的用户ID
	 */
	@ApiModelProperty(value = "观看直播的用户ID")
	private Long userId;

	/**
	 * 直播房间号
	 */
	@ApiModelProperty(value = "直播房间号")
	private Integer roomCode;

	/**
	 *
	 */
	@ApiModelProperty(value = "clientId")
	private Integer clientId;

	/**
	 * IP地址
	 */
	@ApiModelProperty(value = "IP")
	private String ip;

}
