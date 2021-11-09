package com.gejian.live.common.dto.gift;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 直播间礼物管理新增请求dto
 * </p>
 * @author yuanxue
 * @date 2021-09-28
 */
@Data
public class LiveGiftSaveDTO implements Serializable {

	/**
	 * 名字
	 */
	@ApiModelProperty("礼物名字")
	@NotBlank(message = "礼物名字不能为空")
	private String name;

	/**
	 * 类型 0-普通礼物
	 */
	@ApiModelProperty("礼物类型 0-普通礼物")
	@NotBlank(message = "礼物类型不能为空")
	private String type;

	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	private String description;

	/**
	 * 0' COMMENT '状态 0-未上架，1-已上架
	 */
	@ApiModelProperty("状态 0-未上架，1-已上架")
	private Boolean status;

	/**
	 * 静态图
	 */
	@ApiModelProperty("静态图")
	@NotBlank(message = "静态图不能为空")
	private String staticImage;

	/**
	 * 动态图
	 */
	@ApiModelProperty("动态图")
	private String dynamicImage;

	/**
	 * 价格
	 */
	@ApiModelProperty("价格")
	@NotNull(message = "价格不能为空")
	private BigDecimal price;

	/**
	 * 价格单位
	 */
	@ApiModelProperty("价格单位")
	private String priceUnits;

	/**
	 * 弹幕内容
	 */
	@ApiModelProperty("弹幕内容")
	private String barrage;

	/**
	 * 作用-亲密度
	 */
	@ApiModelProperty("亲密度")
	private Integer intimacy;

	/**
	 * 作用-贡献值
	 */
	@ApiModelProperty("贡献值")
	private Integer contribution;

	/**
	 * 作用-主播收益值
	 */
	@ApiModelProperty("主播收益值")
	private BigDecimal earnings;

}
