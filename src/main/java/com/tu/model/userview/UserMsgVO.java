package com.tu.model.userview;

import com.tu.model.messages.MessagesVO;

public class UserMsgVO implements java.io.Serializable {
	
	private static final long serialVersionUID = -5706659091511226929L;
	
	// Fields
	private Integer userId;
	private MessagesVO message;
	private String groupName;
	private String readState;

	// Constructors
	/** default constructor */
	public UserMsgVO() {
		// do nothing
	}

	/** full constructor */

	public UserMsgVO(MessagesVO message, Integer userId, String groupName, String readState) {
		this.message = message;
		this.userId = userId;
		this.groupName = groupName;
		this.readState = readState;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getReadState() {
		return this.readState;
	}

	public void setReadState(String readState) {
		this.readState = readState;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public MessagesVO getMessage() {
		return message;
	}

	public void setMessage(MessagesVO message) {
		this.message = message;
	}

}
