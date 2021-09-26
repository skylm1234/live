package com.gejian.live.service;

import com.gejian.common.core.util.UCloudPictureCensorUtils;
import com.gejian.live.BaseTest;
import com.gejian.live.common.dto.SnapshotInfo;
import com.gejian.live.web.service.ImageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author ：lijianghuai
 * @date ：2021-09-24 11:36
 * @description：
 */

@DisplayName("截图测试")
public class SnapshotTest extends BaseTest {

	@Autowired
	private ImageService imageService;
	@Test
	public void test(){
		Optional<SnapshotInfo> infoOptional = imageService.snapShot(99995);
		Assertions.assertTrue(infoOptional.isPresent());
		SnapshotInfo snapshotInfo = infoOptional.get();
		double censor = UCloudPictureCensorUtils.censor(snapshotInfo.getFileName(),snapshotInfo.getBytes());
		Assertions.assertEquals(1, censor);
	}
}
