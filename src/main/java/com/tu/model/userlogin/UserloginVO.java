package com.tu.model.userlogin;

import java.util.Date;


public class UserloginVO implements java.io.Serializable {
	private static final long serialVersionUID = 4969190418097520154L;
	
	private Integer userId;
	private String userName;
	private String userPassword;
	private Integer userRole;
	private String userDept;
	private Date inDate;
	private Date outDate;
	private Float spending;
	private Float quota;

	public UserloginVO() {
		userId = 0;
		userName = null;
		userPassword = null;
		userRole = 0;
		userDept = null;
		inDate = null;
		outDate = null;
		spending = null;
		quota = null;
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

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
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

	public Date getOutDate() {
		return this.outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Float getSpending() {
		return spending;
	}

	public void setSpending(Float spending) {
		this.spending = spending;
	}

	public Float getQuota() {
		return this.quota;
	}

	public void setQuota(Float quota) {
		this.quota = quota;
	}

}
