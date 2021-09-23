package com.gejian.live.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fengliang
 * @Date 2021/9/22
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntity {
	private String token;
	private Long expireTimestamp;

}
