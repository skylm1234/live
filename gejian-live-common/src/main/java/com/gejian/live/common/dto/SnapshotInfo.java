package com.gejian.live.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author ：lijianghuai
 * @date ：2021-09-24 11:12
 * @description：截图信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnapshotInfo {

	/**
	 * 图片文件名
	 */
	private String fileName;

	/**
	 * 图片二进制
	 */
	private byte[] bytes;

	/**
	 * 房间id
	 */
	private int roomId;

	/**
	 * 创建时间
	 */
	private LocalDateTime createdAt;
}
