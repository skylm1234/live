package com.gejian.live.web.service.impl;

import com.gejian.live.common.util.UCloudSignatureUtils;
import com.gejian.live.web.service.PictureCensorService;
import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author ：lijianghuai
 * @date ：2021-09-17 14:06
 * @description：
 */

@Service
public class UCloudPictureCensorServiceImpl implements PictureCensorService {

	@Value("${gejian.live.censor.ucloud.publickey}")
	private String publicKey;

	@Value("${gejian.live.censor.ucloud.privatekey}")
	private String privateKey;

	@Value("${gejian.live.censor.ucloud.appid}")
	private String appId;


	@Value("${gejian.live.censor.ucloud.url}")
	private String url;

	@Override
	public boolean censor(String picPath) {
		String timestamp = String.valueOf(System.currentTimeMillis());
		Map<String, Object> signatureParmas = ImmutableMap.<String,Object>builder().put("ResourceId", appId).put("PublicKey", publicKey)
				.put("Timestamp", timestamp).build();
		String signature = UCloudSignatureUtils.getSignature(signatureParmas,privateKey);
		FileSystemResource fileSystemResource = new FileSystemResource(picPath);
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("Scenes", "porn");
		param.add("Method", "file");
		param.add("Image",fileSystemResource);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.set("ResourceId",appId);
		headers.set("PublicKey",publicKey);
		headers.set("Signature",signature);
		headers.set("Timestamp", timestamp);
		org.springframework.http.HttpEntity<MultiValueMap<String, Object>> httpEntity = new org.springframework.http.HttpEntity<>(param, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		String responseEntityBody = responseEntity.getBody();
		System.out.println(responseEntityBody);
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseEntityBody);
		int ret = JsonPath.read(document,"$.RetCode");
		if(ret != 0){
			return true;
		}
		int score = JsonPath.read(document,"$.Result.Porn.Score");
		return score > 0.8;
	}

	@Override
	public boolean censor(byte picBytes) {
		return false;
	}

}
