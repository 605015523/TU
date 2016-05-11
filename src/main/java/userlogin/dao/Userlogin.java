package userlogin.dao;

import java.util.Date;

/**
 * Userlogin entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Userlogin implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String userPassword;
	private Integer userRole;
	private String userDept;
	private Date in_date;
	private Date out_date;
	private Float spending;
	private Float quota;

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

	public Date getIn_date() {
		return this.in_date;
	}

	public void setIn_date(Date inDate) {
		this.in_date = inDate;
	}

	public Date getOut_date() {
		return this.out_date;
	}

	public void setOut_date(Date outDate) {
		this.out_date = outDate;
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