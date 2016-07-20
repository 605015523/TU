package com.tu.user.group.dao;

public class UserGroup implements java.io.Serializable {

	private static final long serialVersionUID = 5624706764022407140L;
	
	// Fields
	private Integer userId;
	private Integer groupId;

	public UserGroup() {
	}

	public UserGroup(Integer userId, Integer groupId) {
		this.groupId = groupId;
		this.userId = userId;
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
