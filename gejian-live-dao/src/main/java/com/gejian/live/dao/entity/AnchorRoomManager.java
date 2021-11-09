package com.gejian.live.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 房管表
 * </p>
 *
 * @author fengliang
 * @since 2021-09-28
 */
@Data
public class AnchorRoomManager extends Model<AnchorRoomManager> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 主播id
     */
    @TableField("anchor_id")
    private Long anchorId;

    /**
     * 房管id
     */
    @TableField("manager_id")
    private Long managerId;

    /**
     * 房管昵称
     */
    @TableField("manager_name")
    private String managerName;

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
     * 是否删除 1 删除 0 未删除
     */
    @TableField("deleted")
    private Boolean deleted;


}
