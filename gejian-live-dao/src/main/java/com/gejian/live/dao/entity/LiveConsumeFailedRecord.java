package com.gejian.live.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gejian.live.common.enums.LiveConsumeFailedType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ：lijianghuai
 * @date ：2021-09-22 16:20
 * @description：消费失败记录表
 */
@Data
@TableName("live_consume_failed_record")
public class LiveConsumeFailedRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("msg_body")
    private String msgBody;

	@TableField("msg_type")
	private LiveConsumeFailedType msgType;

	@TableField("error_message")
	private String errorMessage;

	@TableField("create_time")
	private LocalDateTime createTime;

	@TableField("update_time")
    private LocalDateTime updateTime;

	@TableField("deleted")
    private Boolean deleted;


}
