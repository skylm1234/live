package com.gejian.live.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user_watch_online")
public class UserWatchOnline {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 观看用户ID
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 房间号
	 */
	@TableField("room_id")
	private Integer roomId;

	/**
	 * 
	 */
	@TableField("client_id")
	private Integer clientId;

	/**
	 * IP地址
	 */
	@TableField("ip")
	private String ip;

	/**
	 * 开始时间
	 */
	@TableField("create_time")
	private LocalDateTime createTime;


}