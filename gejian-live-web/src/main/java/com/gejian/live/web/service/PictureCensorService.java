package com.gejian.live.web.service;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 14:05
 * @description：
 */
public interface PictureCensorService {

	boolean censor(String picPath);

	boolean censor(byte picBytes);
}
