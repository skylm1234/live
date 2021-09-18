package com.gejian.live.web.verification;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 16:53
 * @description：校验器
 */

public interface Validator {

	/**
	 * 校验
	 * @param request
	 */
	void valid(VerifyRequest request);

	interface Chain {

		/**
		 * 执行
		 * @param request
		 */
		void process(VerifyRequest request);
	}
}
