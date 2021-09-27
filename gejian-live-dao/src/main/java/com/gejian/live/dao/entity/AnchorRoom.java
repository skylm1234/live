package com.gejian.live.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author yuanxue
 * @since 2021-09-22
 */
@Data
public class AnchorRoom extends Model<AnchorRoom> {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 直播用户ID
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 房间号
	 */
	@TableField("room_id")
	private Integer roomId;

	/**
	 * 直播状态 
	 */
	@TableField("live_status")
	private Integer liveStatus;

	/**
	 * 主播状态
	 */
	@TableField("anchor_status")
	private Integer anchorStatus;

	/**
	 * 房间描述
	 */
	@TableField("room_description")
	private String roomDescription;

	/**
	 * 房间标题
	 */
	@TableField("room_title")
	private String roomTitle;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@TableField("update_time")
	private LocalDateTime updateTime;

	/**
	 * 0' COMMENT '是否删除，1-true-删除，0-false-未删除
	 */
	@TableField("deleted")
	private boolean deleted;

}