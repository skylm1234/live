package com.gejian.live.service;

import com.gejian.common.core.util.UCloudPictureCensorUtils;
import com.gejian.live.BaseTest;
import com.gejian.live.web.ffmpeg.SnapShotUtil;
import com.gejian.live.web.ffmpeg.SnapshotInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author ：lijianghuai
 * @date ：2021-09-24 11:36
 * @description：
 */

@DisplayName("截图测试")
public class SnapshotTest extends BaseTest {
	@Test
	public void test(){
		SnapshotInfo snapshotInfo = SnapShotUtil.snapShot(99995).orElseThrow(RuntimeException::new);
		Assertions.assertTrue(snapshotInfo.getBytes().length > 0);
		double censor = UCloudPictureCensorUtils.censor(snapshotInfo.getFileName(),snapshotInfo.getBytes());
		Assertions.assertEquals(1, censor);
	}
}
