package com.gejian.live.web.verification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 16:54
 * @description：校验结果
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyResult {

	private boolean pass;

	private String info;

	@Override
	public String toString() {
		return "Result [pass=" + pass + ", info=" + info + "]";
	}

}
