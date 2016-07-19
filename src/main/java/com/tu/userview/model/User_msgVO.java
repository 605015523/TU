package com.tu.userview.model;

public class User_msgVO implements java.io.Serializable {
	
	private static final long serialVersionUID = -5706659091511226929L;
	
	// Fields
	private Integer msgId;
	private Integer actId;
	private String groupName;
	private String actName;
	private Float actMoney;
	private String description;
	private String dateRange;
	private String actDate;
	private String state;
	private String readState;

	// Constructors
	/** default constructor */
	public User_msgVO() {
	}

	/** full constructor */

	public User_msgVO(Integer msgId, Integer actId, String groupName,
			String actName, Float actMoney, String description,
			String dateRange, String actDate, String state, String readState) {
		this.msgId = msgId;
		this.actId = actId;
		this.groupName = groupName;
		this.actName = actName;
		this.actMoney = actMoney;
		this.description = description;
		this.dateRange = dateRange;
		this.actDate = actDate;
		this.state = state;
		this.readState = readState;
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

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public String getActDate() {
		return this.actDate;
	}

	public void setActDate(String actdate) {
		this.actDate = actdate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReadState() {
		return this.readState;
	}

	public void setReadState(String readState) {
		this.readState = readState;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

}
