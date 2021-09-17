package com.gejian.live.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 11:45
 * @description：
 */

@Slf4j
public class UCloudSignatureUtils {

	public static String getSignature(Map<String,Object> signatureParams,String privateKey){
		SortedMap<String,Object> sortedMap = new TreeMap<>(signatureParams);
		StringBuilder builder = new StringBuilder();
		sortedMap.forEach((key,value) -> builder.append(key).append(value));
		builder.append(privateKey);
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(builder.toString().getBytes());
			byte[] messageDigest = digest.digest();
			return FormatUtils.formatBytes2HexString(messageDigest, false);
		} catch (NoSuchAlgorithmException e) {
			log.error("get signature error",e);
		}

		return StringUtils.EMPTY;
	}
}
