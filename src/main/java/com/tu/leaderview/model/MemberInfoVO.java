package com.tu.leaderview.model;

public class MemberInfoVO implements java.io.Serializable {
	private static final long serialVersionUID = 827937561865560546L;
	
	// Fields
	private Integer userId;
	private String userName;
	private String userDept;

	public MemberInfoVO() {
		userId = null;
		userName = null;
		userDept = null;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDept() {
		return this.userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

}