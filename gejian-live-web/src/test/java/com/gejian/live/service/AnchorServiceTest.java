package com.gejian.live.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gejian.live.BaseTest;
import com.gejian.live.dao.entity.Anchor;
import com.gejian.live.web.service.AnchorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：lijianghuai
 * @date ：2021-10-14 14:34
 * @description：
 */
public class AnchorServiceTest extends BaseTest {

	@Autowired
	private AnchorService anchorService;

	@Test
	public void test(){
		Page page = anchorService.lambdaQuery().like(Anchor::getRoomTitle, "%玩").like(Anchor::getRoomDescription,"%蛋").page(new Page(1, 10));

	}
}
