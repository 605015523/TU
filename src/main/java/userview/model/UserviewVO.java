package userview.model;

import java.util.Date;
import java.util.List;

public class UserviewVO implements java.io.Serializable {
	// Fields
	private Integer userId;
	private String userName;
	private List<String> groupName;
	private String userPassword;
	private String userDept;
	private Date in_date;
	private Float spending;
	private Float quota;
	private Float remaining;
	private List<String> actName;

	public UserviewVO() {

		userId = null;
		userName = null;
		groupName = null;
		userPassword = null;
		userDept = null;
		in_date = null;
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

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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

	public Float getSpending() {
		return spending;
	}

	public void setSpending(Float spending) {
		this.spending = spending;
	}

	public List<String> getActName() {
		return actName;
	}

	public void setActName(List<String> actName) {
		this.actName = actName;
	}

	public Float getQuota() {
		return this.quota;
	}

	public void setQuota(Float quota) {
		this.quota = quota;
	}

	public Float getRemaining() {
		return this.remaining;
	}

	public void setRemaining(Float remaining) {
		this.remaining = remaining;
	}

}
