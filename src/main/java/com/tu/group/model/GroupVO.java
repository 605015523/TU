package com.tu.group.model;

public class GroupVO implements java.io.Serializable {

	private Integer groupId;
	private String groupName;
	private Integer groupLeaderId;
	private String description;

	public GroupVO() {
		groupId = null;
		groupName = null;
		groupLeaderId = null;
		description = null;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupLeaderId() {
		return this.groupLeaderId;
	}

	public void setGroupLeaderId(Integer groupLeaderId) {
		this.groupLeaderId = groupLeaderId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
