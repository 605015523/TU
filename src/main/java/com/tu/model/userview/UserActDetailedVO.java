package com.tu.model.userview;

import com.tu.model.user.act.UserActVO;

public class UserActDetailedVO extends UserActVO {
	private static final long serialVersionUID = -1608977902835248916L;
	
	private String actName;
	private String group;
	private Float actMoney;
	private String actDate;
	private String state;
	private String description;

	public UserActDetailedVO() {
		super();
		actName = null;
		group = null;
		actMoney = null;
		actDate = null;
		state = null;
		description = null;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Float getActMoney() {
		return actMoney;
	}

	public void setActMoney(Float actMoney) {
		this.actMoney = actMoney;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String string) {
		this.actDate = string;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
