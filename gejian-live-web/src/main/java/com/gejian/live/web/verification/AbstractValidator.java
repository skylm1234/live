package com.gejian.live.web.verification;

/**
 * @author ：lijianghuai
 * @date ：2021-09-18 11:16
 * @description：
 */
public abstract class AbstractValidator implements Validator {

	protected AbstractValidator nextValidator;

	public void setNext(AbstractValidator nextValidator){
		this.nextValidator = nextValidator;
	}

	public AbstractValidator getNext() {
		return nextValidator;
	}

	public void doValid(VerifyRequest verifyRequest){
		valid(verifyRequest);
		AbstractValidator next = getNext();
		if(next != null){
			next.doValid(verifyRequest);
		}
	}
}
