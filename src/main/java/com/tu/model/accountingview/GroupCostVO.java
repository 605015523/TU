package com.tu.model.accountingview;

import java.math.BigDecimal;

public class GroupCostVO implements java.io.Serializable {

	private static final long serialVersionUID = 5847774643475968642L;
	
	private Integer groupId;
	private BigDecimal cost;

	// Constructors
	/** default constructor */
	public GroupCostVO() {
		groupId = null;
		cost = BigDecimal.ZERO;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

}
