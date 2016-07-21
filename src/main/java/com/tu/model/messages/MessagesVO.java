package com.tu.model.messages;

import java.util.Date;

public class MessagesVO implements java.io.Serializable {

	private static final long serialVersionUID = -3582074631065605747L;
	
	private Integer msgId;
	private Integer actId;
	private Integer groupId;
	private String actName;
	private Float actMoney;
	private String description;
	private Date enrollStartDate;
	private Date enrollEndDate;
	private Date actDate;
	private String state;

	// Constructors
	/** default constructor */
	public MessagesVO() {
		msgId = null;
		actId = null;
		groupId = null;
		actName = null;
		actMoney = null;
		description = null;
		enrollStartDate = null;
		enrollEndDate = null;
		actDate = null;
		state = null;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public Float getActMoney() {
		return this.actMoney;
	}

	public void setActMoney(Float actMoney) {
		this.actMoney = actMoney;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEnrollStartDate() {
		return this.enrollStartDate;
	}

	public void setEnrollStartDate(Date enrollStartDate) {
		this.enrollStartDate = enrollStartDate;
	}

	public Date getEnrollEndDate() {
		return this.enrollEndDate;
	}

	public void setEnrollEndDate(Date enrollEndDate) {
		this.enrollEndDate = enrollEndDate;
	}

	public Date getActDate() {
		return this.actDate;
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

}
