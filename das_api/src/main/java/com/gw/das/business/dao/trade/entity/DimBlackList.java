package com.gw.das.business.dao.trade.entity;

import com.gw.das.business.common.orm.Column;
import com.gw.das.business.dao.base.BaseModel;

/**
 * 风控要素列表
 */
public class DimBlackList extends BaseModel {

	/** rowKey */
	@Column(name = "rowkey")
	private String rowKey;
	/** 类型(account_no,account_name,mobile,id_card_md5,email,ip) */
	@Column(name = "type")
	private String type;
	/** 类型值(手机号时去掉前缀) */
	@Column(name = "value")
	private String value;
	/** 风险类型 */
	@Column(name = "risk_type")
	private String riskType;
	/** 创建时间 */
	@Column(name = "create_time")
	private String createTime;
	/** 更新时间 */
	@Column(name = "update_time")
	private String updateTime;
	/** 标记时间 */
	@Column(name = "mark_time")
	private String markTime;
	/** 来源标记(1自动,2手工,3自动&手工) */
	@Column(name = "source")
	private int source;
	/** 原因备注 */
	@Column(name = "remark")
	private String remark;
	/** 原因备注英文 */
	@Column(name = "remark_en")
	private String remarkEn;
	
	public String getRowKey() {
		return rowKey;
	}
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRiskType() {
		return riskType;
	}
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getMarkTime() {
		return markTime;
	}
	public void setMarkTime(String markTime) {
		this.markTime = markTime;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemarkEn() {
		return remarkEn;
	}
	public void setRemarkEn(String remarkEn) {
		this.remarkEn = remarkEn;
	}
}
