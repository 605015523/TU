package com.tu.model.userview;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UserviewVO implements java.io.Serializable {
	private static final long serialVersionUID = -3537565081639531832L;
	
	// Fields
	private Integer userId;
	private String userName;
	private List<String> groupName;
	private String userDept;
	private Date inDate;
	private BigDecimal spending;
	private BigDecimal quota;
	private BigDecimal remaining;
	private List<String> actName;

	public UserviewVO() {

		userId = null;
		userName = null;
		groupName = null;
		userDept = null;
		inDate = null;
		spending = null;
		quota = null;
		remaining = null;
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

	public List<String> getGroupName() {
		return this.groupName;
	}

	public void setGroupName(List<String> groupName) {
		this.groupName = groupName;
	}

	public String getUserDept() {
		return this.userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public Date getInDate() {
		return this.inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public BigDecimal getSpending() {
		return spending;
	}

	public void setSpending(BigDecimal spending) {
		this.spending = spending;
	}

	public List<String> getActName() {
		return actName;
	}

	public void setActName(List<String> actName) {
		this.actName = actName;
	}

	public BigDecimal getQuota() {
		return this.quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public BigDecimal getRemaining() {
		return this.remaining;
	}

	public void setRemaining(BigDecimal remaining) {
		this.remaining = remaining;
	}

}
