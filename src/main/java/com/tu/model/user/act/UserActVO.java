package com.tu.model.user.act;

import com.tu.model.userlogin.UserloginVO;

public class UserActVO implements java.io.Serializable {

	private static final long serialVersionUID = 983685594099729166L;
	
	// Fields
	private Integer actId;
	private UserloginVO user;
	private Integer nbParticipants;
	private Float consumption;
	private String remark;

	public UserActVO() {
		actId = null;
		user = null;
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

	public UserloginVO getUser() {
		return this.user;
	}

	public void setUser(UserloginVO user) {
		this.user = user;
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
