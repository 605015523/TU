package com.tu.user_act.dao;

public class User_act implements java.io.Serializable {

	// Filed
	private Integer actId;
	private Integer userId;
	private Integer participatorNO;
	private Float consumption;
	private String remark;

	public User_act() {
	}

	public User_act(Integer actId, Integer userId, Integer participatorNO,
			Float consumption, String remark) {
		this.actId = actId;
		this.userId = userId;
		this.participatorNO = participatorNO;
		this.consumption = consumption;
		this.remark = remark;
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
