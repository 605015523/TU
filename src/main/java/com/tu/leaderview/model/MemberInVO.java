package com.tu.leaderview.model;

public class MemberInVO implements java.io.Serializable {

	private static final long serialVersionUID = -3138622175864772428L;
	
	private Integer userId;
	private String userName;
	private String userDept;
	private Integer nbParticipants;
	private Float consumption;
	private String remark;

	public MemberInVO() {
	}

	public MemberInVO(Integer userId, String userName, String userDept,
			Integer nbParticipants, Float consumption, String remark) {
		this.userId = userId;
		this.userName = userName;
		this.userDept = userDept;
		this.nbParticipants = nbParticipants;
		this.consumption = consumption;
		this.remark = remark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getNbParticipants() {
		return nbParticipants;
	}

	public void setNbParticipants(Integer nbParticipants) {
		this.nbParticipants = nbParticipants;
	}

	public Float getConsumption() {
		return consumption;
	}

	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

}
