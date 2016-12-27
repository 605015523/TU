package com.tu.model.userview;

import java.math.BigDecimal;
import java.util.Date;

import com.tu.model.user.act.UserActVO;

public class UserActDetailedVO extends UserActVO {
	private static final long serialVersionUID = -1608977902835248916L;
	
	private String actName;
	private String group;
	private BigDecimal actMoney;
	private Date actDate;
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

	public BigDecimal getActMoney() {
		return actMoney;
	}

	public void setActMoney(BigDecimal actMoney) {
		this.actMoney = actMoney;
	}

	public Date getActDate() {
		return actDate;
	}

	public void setActDate(Date actDate) {
		this.actDate = actDate;
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
