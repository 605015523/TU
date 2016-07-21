package com.tu.model.user.group;

public class UserGroupVO implements java.io.Serializable {

	private static final long serialVersionUID = -6214972815023614321L;
	
	// Fields
	private Integer userId;
	private Integer groupId;

	public UserGroupVO() {
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
