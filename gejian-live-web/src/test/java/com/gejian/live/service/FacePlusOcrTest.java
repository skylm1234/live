package com.gejian.live.service;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.gejian.common.core.constant.SecurityConstants;
import com.gejian.common.core.util.R;
import com.gejian.live.BaseTest;
import com.gejian.third.client.RemoteGejianThirdService;
import com.gejian.third.client.dto.OcrBankcardRequestDTO;
import com.gejian.third.client.dto.OcrBankcardResponseDTO;
import com.gejian.third.client.dto.OcridcardRequestDTO;
import com.gejian.third.client.dto.OcridcardResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;

/**
 * @author ：lijianghuai
 * @date ：2021-09-27
 * @description：
 */

@DisplayName("face++识别")
public class FacePlusOcrTest extends BaseTest {


	@Autowired
	private RemoteGejianThirdService remoteGejianThirdService;


	@Test
	@DisplayName("识别身份证")
	public void testocridcard() throws Exception {
		OcridcardRequestDTO ocridcardRequestDTO = new OcridcardRequestDTO();
		//byte[] bytes = IoUtil.readBytes(new FileInputStream("d:\\身份证正面.jpg"));
		byte[] bytes = IoUtil.readBytes(new FileInputStream("d:\\1.jpg"));
		BASE64Encoder encoder = new BASE64Encoder();
		String base64Image = encoder.encode(bytes);
		ocridcardRequestDTO.setImageBase64(base64Image);
		R<OcridcardResponseDTO>  result = remoteGejianThirdService.ocridcard(ocridcardRequestDTO, SecurityConstants.FROM_IN);
		System.out.println(JSON.toJSONString(result.getData()));
	}

	@Test
	@DisplayName("识别银行卡")
	public void testocrbankcard() throws Exception {
		OcrBankcardRequestDTO ocrBankcardRequestDTO = new OcrBankcardRequestDTO();
		byte[] bytes = IoUtil.readBytes(new FileInputStream("d:\\银行卡.jpg"));
		BASE64Encoder encoder = new BASE64Encoder();
		String base64Image = encoder.encode(bytes);
		ocrBankcardRequestDTO.setImageBase64(base64Image);
		R<OcrBankcardResponseDTO>  result = remoteGejianThirdService.ocrbankcard(ocrBankcardRequestDTO, SecurityConstants.FROM_IN);
		System.out.println(JSON.toJSONString(result.getData()));
	}
}
