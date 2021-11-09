package com.gejian.live.web.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.gejian.live.web.action.enums.ActionEnum;
import com.gejian.live.web.action.event.Action;
import com.gejian.live.web.action.params.ActionParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 11:30
 * @description：
 */

@Component
@Slf4j
public class LiveActionInterceptor implements HandlerInterceptor {

	@Autowired
	private Map<String, Action> actionMap;

	private static final String JSON_START = "{";
	private static final String JSON_END = "}";
	private static final String URL_PARAM_PREFIX = "?";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if(!(handler instanceof HandlerMethod )){
			log.error("hander not accept ...");
			return true;
		}
		try{
			String body = request.getReader().lines().collect(Collectors.joining());
			log.info("srs callback body:===== {}",body);
			if(StrUtil.isNotBlank(body) && body.startsWith(JSON_START) && body.endsWith(JSON_END)){
				JSONObject jsonObject = JSONObject.parseObject(body);
				String action = jsonObject.getString("action");
				if(StrUtil.isBlank(action)){
					return true;
				}
				ActionEnum actionEnum = ActionEnum.valueOf(action.toUpperCase());
				ActionParams actionParams = actionEnum.params();
				BeanUtil.copyProperties(jsonObject,actionParams);
				actionParams.setExtra(extra(jsonObject));
				Action currentAction = actionMap.get(action);
				currentAction.execute(actionParams);
				// 需要把body传递下去，不然spring去拿就没有流了
				CustomHttpServletRequestWrapper requestWrapper = (CustomHttpServletRequestWrapper) request;
				requestWrapper.setBody(body);
				return true;
			}
		}catch (Exception e){
			log.error("callback execute error,",e);
			return false;
		}
		return false;
	}

	private Map<String,String> extra(JSONObject jsonObject){
		Map<String,String> map = new HashMap<>();
		String extra = jsonObject.getString("param");
		if(StrUtil.isNotBlank(extra)){
			if(extra.startsWith(URL_PARAM_PREFIX)){
				extra = extra.substring(1);
			}
			List<String> split = StrUtil.split(extra, "&");
			split.forEach(s ->{
				String[] params = s.split("=");
				map.put(params[0],params[1]);
			});
		}
		return map;
	}
}
