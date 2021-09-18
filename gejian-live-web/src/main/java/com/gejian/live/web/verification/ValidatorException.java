package com.gejian.live.web.verification;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 17:26
 * @description：
 */
public class ValidatorException extends RuntimeException{

	public ValidatorException(String message){
		super(message);
	}

	public ValidatorException(String message,Throwable cause){
		super(message,cause);
	}
}
