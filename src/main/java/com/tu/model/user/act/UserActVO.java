package com.tu.model.user.act;

public class UserActVO implements java.io.Serializable {

	private static final long serialVersionUID = 983685594099729166L;
	
	// Fields
	private Integer actId;
	private Integer userId;
	private Integer nbParticipants;
	private Float consumption;
	private String remark;

	public UserActVO() {
		actId = null;
		userId = null;
		nbParticipants = null;
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

	public Integer getNbParticipants() {
		return this.nbParticipants;
	}

	public void setNbParticipants(Integer nbParticipants) {
		this.nbParticipants = nbParticipants;
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
