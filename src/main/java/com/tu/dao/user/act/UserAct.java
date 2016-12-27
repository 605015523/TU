package com.tu.dao.user.act;

import java.math.BigDecimal;

public class UserAct implements java.io.Serializable {

	private static final long serialVersionUID = -3072989564300722466L;
	
	// Filed
	private Integer actId;
	private Integer userId;
	private Integer nbParticipants;
	private BigDecimal consumption;
	private String remark;

	public UserAct() {
	}

	public UserAct(Integer actId, Integer userId, Integer nbParticipants,
			BigDecimal consumption, String remark) {
		this.actId = actId;
		this.userId = userId;
		this.nbParticipants = nbParticipants;
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

	public Integer getNbParticipants() {
		return this.nbParticipants;
	}

	public void setNbParticipants(Integer nbParticipants) {
		this.nbParticipants = nbParticipants;
	}

	public BigDecimal getConsumption() {
		return this.consumption;
	}

	public void setConsumption(BigDecimal consumption) {
		this.consumption = consumption;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
