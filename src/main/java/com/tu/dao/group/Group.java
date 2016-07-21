package com.tu.dao.group;

public class Group implements java.io.Serializable {

	private static final long serialVersionUID = -2016298182510150987L;
	
	// Fields
	private Integer groupId;
	private String groupName;
	private Integer groupLeaderId;
	private String description;

	// Constructors
	public Group() {
	}

	/** minimal constructor */
	public Group(String groupName) {
		this.groupName = groupName;
	}

	/** full constructor */
	public Group(String groupName, Integer groupLeaderId, String description) {
		this.groupName = groupName;
		this.groupLeaderId = groupLeaderId;
		this.description = description;

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
