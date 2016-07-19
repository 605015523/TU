package com.tu.messages.dao;

import java.util.Date;

public class Messages implements java.io.Serializable {
	private static final long serialVersionUID = -2301807989217269139L;
	
	// Fields
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
	public Messages() {
	}

	/** full constructor */

	public Messages(Integer msgId, Integer actId, Integer groupId,
			String actName, Float actMoney, String description,
			Date enrollStartDate, Date enrollEndDate, Date actDate, String state) {
		this.msgId = msgId;
		this.actId = actId;
		this.groupId = groupId;
		this.actName = actName;
		this.actMoney = actMoney;
		this.description = description;
		this.enrollStartDate = enrollStartDate;
		this.enrollEndDate = enrollEndDate;
		this.actDate = actDate;
		this.state = state;
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
