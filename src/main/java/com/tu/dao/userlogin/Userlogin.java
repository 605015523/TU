package com.tu.dao.userlogin;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Userlogin entity.
 * 
 */

public class Userlogin implements java.io.Serializable {

	private static final long serialVersionUID = -4693396900142694692L;
	
	// Fields
	private Integer userId;
	private String userName;
	private String userPassword;
	private Integer userRole;
	private String userDept;
	private Date inDate;
	private Date outDate;
	private BigDecimal spending;
	private BigDecimal quota;

	// Constructors

	/** default constructor */
	public Userlogin() {
	}

	/** minimal constructor */
	public Userlogin(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}

	/** full constructor */
	public Userlogin(String userName, String userPassword, Integer userRole,
			String userDept) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userDept = userDept;
	}

	// Property accessors

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

	public BigDecimal getSpending() {
		return spending;
	}

	public void setSpending(BigDecimal spending) {
		this.spending = spending;
	}

	public BigDecimal getQuota() {
		return this.quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

}