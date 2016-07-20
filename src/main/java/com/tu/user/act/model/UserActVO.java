package com.tu.user.act.model;

public class UserActVO implements java.io.Serializable {

	private static final long serialVersionUID = 983685594099729166L;
	
	// Fields
	private Integer actId;
	private Integer userId;
	private Integer participatorNO;
	private Float consumption;
	private String remark;

	public UserActVO() {
		actId = null;
		userId = null;
		participatorNO = null;
		consumption = null;
		remark = null;
	}

	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getParticipatorNO() {
		return this.participatorNO;
	}

	public void setParticipatorNO(Integer participatorNO) {
		this.participatorNO = participatorNO;
	}

	public Float getConsumption() {
		return this.consumption;
	}

	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
