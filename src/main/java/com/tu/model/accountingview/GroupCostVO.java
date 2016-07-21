package com.tu.model.accountingview;

public class GroupCostVO implements java.io.Serializable {

	private static final long serialVersionUID = 5847774643475968642L;
	
	private Integer groupId;
	private float cost;

	// Constructors
	/** default constructor */
	public GroupCostVO() {
		groupId = null;
		cost = 0;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}
