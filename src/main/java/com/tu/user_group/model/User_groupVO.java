package com.tu.user_group.model;

public class User_groupVO implements java.io.Serializable {

	private static final long serialVersionUID = -6214972815023614321L;
	
	// Fields
	private Integer userId;
	private Integer groupId;

	public User_groupVO() {
		userId = null;
		groupId = null;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
