package com.gejian.live.common.dto.streamer;

import lombok.Data;

/**
 * @Author:chen
 * @Date: 2021/9/22 11:34
 */
@Data
public class StreamerOnlineAdd {

	private Long userId;

	private Integer roomCode;

	private String clientId;

	private String ip;
}