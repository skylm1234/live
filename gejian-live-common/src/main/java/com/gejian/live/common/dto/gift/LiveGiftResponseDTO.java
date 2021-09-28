package com.gejian.live.common.dto.gift;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * p>
 * 直播间礼物管理响应dto
 * </p>
 * @author yuanxue
 * @date 2021-09-22
 */
@Data
public class LiveGiftResponseDTO implements Serializable {

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	private Long id;

	/**
	 * 名字
	 */
	@ApiModelProperty("名字")
	private String name;

	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	private String description;

	/**
	 * 类型 0-普通礼物
	 */
	@ApiModelProperty("类型 0-普通礼物")
	private String type;

	/**
	 * 0' COMMENT '状态 0-未上架，1-已上架
	 */
	@ApiModelProperty("状态 0-未上架，1-已上架")
	private Boolean status;

	/**
	 * 静态图
	 */
	@ApiModelProperty("静态图")
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

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty("修改时间")
	private LocalDateTime updateTime;


}
