package com.gejian.live.common.dto.gift;

import com.gejian.common.core.util.BasePageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 直播间礼物管理删除请求dto
 * </p>
 * @author yuanxue
 * @date 2021-09-28
 */
@Data
public class LiveGiftDelDTO extends BasePageQuery {

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	@NotNull(message = "主键不能为空")
	private List<Long> ids;


}
