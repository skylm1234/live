package com.gejian.live.web.verification;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：lijianghuai
 * @date ：2021-09-18 9:43
 * @description：
 */
public abstract class AbstractValidChain implements Validator.Chain {

	@Autowired
	private List<AbstractValidator> validators;

	protected AbstractValidator validator;

	@Override
	public void process(VerifyRequest request) {
		assert validator != null;
		validator.doValid(request);
	}

	protected void setValidator(ValidType validType){
		List<AbstractValidator> pullValidators = validators.stream().filter(validator -> {
			Valid valid = validator.getClass().getAnnotation((Valid.class));
			if (valid != null) {
				ValidType[] types = valid.type();
				for (ValidType type : types) {
					if (type == validType) {
						return true;
					}
				}
			}
			return false;
		}).collect(Collectors.toList());
		for(int i = 0; i < pullValidators.size() - 1; i++){
			pullValidators.get(i).setNext(pullValidators.get(i + 1));
		}
		validator = pullValidators.get(0);
	}

}
