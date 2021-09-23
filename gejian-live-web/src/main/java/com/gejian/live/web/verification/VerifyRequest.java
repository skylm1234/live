package com.gejian.live.web.verification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 16:59
 * @description：校验请求
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyRequest {

	int userId;

	String roomId;

	String token;

	long expireTimestamp;
}
