package com.gejian.live.common.dto.gift;

import com.gejian.common.core.util.BasePageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 直播间礼物管理查询请求dto
 * </p>
 * @author yuanxue
 * @date 2021-09-28
 */
@Data
public class LiveGiftPageDTO extends BasePageQuery {

	/**
	 * 名字
	 */
	@ApiModelProperty("礼物名字")
	private String name;

	/**
	 * 类型 0-普通礼物
	 */
	@ApiModelProperty("礼物类型 0-普通礼物")
	private String type;


}
