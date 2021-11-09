package com.gejian.live.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author yuanxue
 * @since 2021-09-28
 */
@Data
@TableName("live_gift")
public class LiveGift {
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 名字
	 */
	@TableField("name")
	private String name;

	/**
	 * 描述
	 */
	@TableField("description")
	private String description;

	/**
	 * 类型 0-普通礼物
	 */
	@TableField("type")
	private String type;

	/**
	 * 0' COMMENT '状态 0-未上架，1-已上架
	 */
	@TableField("status")
	private Boolean status;

	/**
	 * 静态图
	 */
	@TableField("static_image")
	private String staticImage;

	/**
	 * 动态图
	 */
	@TableField("dynamic_image")
	private String dynamicImage;

	/**
	 * 价格
	 */
	@TableField("price")
	private BigDecimal price;

	/**
	 * 价格单位
	 */
	@TableField("price_units")
	private String priceUnits;

	/**
	 * 弹幕内容
	 */
	@TableField("barrage")
	private String barrage;

	/**
	 * 作用-亲密度
	 */
	@TableField("intimacy")
	private Integer intimacy;

	/**
	 * 作用-贡献值
	 */
	@TableField("contribution")
	private Integer contribution;

	/**
	 * 作用-主播收益值
	 */
	@TableField("earnings")
	private BigDecimal earnings;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@TableField("update_time")
	private LocalDateTime updateTime;

	/**
	 * 0' COMMENT '是否删除标记 1是 0否
	 */
	@TableField("deleted")
	private Boolean deleted;

}