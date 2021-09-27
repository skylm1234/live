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
public class Anchor extends Model<Anchor> {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 主播用户id
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 房间描述
	 */
	@TableField("room_description")
	private String roomDescription;

	/**
	 * 房间主题
	 */
	@TableField("room_title")
	private String roomTitle;

	/**
	 * 申请状态 0待审核 1审核成功 2审核失败
	 */
	@TableField("status")
	private Integer status;

	/**
	 * 申请时间
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