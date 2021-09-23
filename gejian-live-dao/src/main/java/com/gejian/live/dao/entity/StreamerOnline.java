package com.gejian.live.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("streamer_online")
public class StreamerOnline {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 主播用户ID
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 主播房间编号
	 */
	@TableField("room_code")
	private Integer roomCode;

	/**
	 * 封面文件存储key
	 */
	@TableField("room_cover_file_name")
	private String roomCoverFileName;

	/**
	 * 封面文件存储桶名称
	 */
	@TableField("room_cover_file_bucket_name")
	private String roomCoverFileBucketName;

	/**
	 * 
	 */
	@TableField("client_id")
	private String clientId;

	/**
	 * 
	 */
	@TableField("ip")
	private String ip;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private LocalDateTime createTime;


}