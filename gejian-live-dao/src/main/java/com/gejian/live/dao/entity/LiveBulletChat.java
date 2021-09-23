package com.gejian.live.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ：lijianghuai
 * @date ：2021-09-22 16:17
 * @description：
 */
@Data
@TableName("live_bullet_chat")
public class LiveBulletChat extends Model<LiveBulletChat> {


	@TableId("id")
	private Long id;
    /**
     * 弹幕内容
     */
    @TableField("content")
    private String content;

	@TableField("room_id")
	private Integer roomId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 是否删除 1 已删除 0 未删除
     */
    @TableField("deleted")
    private String deleted;

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

}
