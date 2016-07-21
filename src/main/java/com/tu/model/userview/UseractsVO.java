package com.tu.model.userview;

public class UseractsVO {
	private Integer userId;
	private Integer actId;
	private String actName;
	private String group;
	private Float actMoney;
	private String actDate;
	private Integer participaterNO;
	private Float consumption;
	private String state;
	private String remark;
	private String description;

	public UseractsVO() {
		userId = null;
		actId = null;
		actName = null;
		group = null;
		actMoney = null;
		actDate = null;
		participaterNO = null;
		consumption = null;
		state = null;
		remark = null;
		description = null;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Float getActMoney() {
		return actMoney;
	}

	public void setActMoney(Float actMoney) {
		this.actMoney = actMoney;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String string) {
		this.actDate = string;
	}

	public Integer getParticipaterNO() {
		return participaterNO;
	}

	public void setParticipaterNO(Integer participaterNO) {
		this.participaterNO = participaterNO;
	}

	public Float getConsumption() {
		return consumption;
	}

	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
