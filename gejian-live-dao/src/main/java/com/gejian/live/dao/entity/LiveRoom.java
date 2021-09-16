package com.gejian.live.dao.entity;

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
 * @author fengliang
 * @since 2021-09-16
 */
@Data
public class LiveRoom extends Model<LiveRoom> {


    /**
     * 主键id
     */
    @TableId(value = "id")
    private Integer id;

	/**
	 * 房间号
	 */
	@TableField("room_id")
	private String roomId;

    /**
     * 是否靓号 1是 0不是
     */
    @TableField("is_beautiful_number")
    private Boolean isBeautifulNumber;

    /**
     * 是否占用 1占用 0未占用
     */
    @TableField("is_occupy")
    private Boolean isOccupy;

    /**
     * 是否删除 1删除 0未删除
     */
    @TableField("deleted")
    private Boolean deleted;

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
     * 版本号
     */
    @TableField("version")
    private Integer version;




}
