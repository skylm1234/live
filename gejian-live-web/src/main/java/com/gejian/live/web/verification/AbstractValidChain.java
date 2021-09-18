package com.gejian.live.web.verification;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

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
		ListMultimap<Integer,AbstractValidator> map = Multimaps.newListMultimap(new TreeMap<>(),ArrayList::new);
		validators.forEach(validator -> {
			Valid valid = validator.getClass().getAnnotation((Valid.class));
			if (valid != null) {
				ValidType[] types = valid.type();
				for (ValidType type : types) {
					if (type == validType) {
						int order =valid.order();
						map.put(order,validator);
					}
				}
			}
		});
		Collection<AbstractValidator> validators = map.values();
		List<AbstractValidator> list = new ArrayList<>(validators);
		for(int i = 0; i < list.size() - 1; i++){
			list.get(i).setNext(list.get(i + 1));
		}
		validator = list.get(0);
	}

}
