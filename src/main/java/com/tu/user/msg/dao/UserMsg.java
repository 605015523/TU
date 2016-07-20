package com.tu.user.msg.dao;

public class UserMsg implements java.io.Serializable {

	private static final long serialVersionUID = 2496672216523471838L;
	
	private Integer userId;
	private Integer msgId;
	private String readState;

	public UserMsg() {

	}

	public UserMsg(Integer userId, Integer msgId, String readState) {
		this.userId = userId;
		this.msgId = msgId;
		this.readState = readState;

	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getReadState() {
		return readState;
	}

	public void setReadState(String readState) {
		this.readState = readState;
	}

}