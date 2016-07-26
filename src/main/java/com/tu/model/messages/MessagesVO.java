package com.tu.model.messages;

import com.tu.model.activities.ActivityVO;

public class MessagesVO implements java.io.Serializable {

	private static final long serialVersionUID = -3582074631065605747L;
	
	private Integer msgId;
	private ActivityVO activity;

	// Constructors
	/** default constructor */
	public MessagesVO() {
		// do nothing
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public ActivityVO getActivity() {
		return activity;
	}

	public void setActivity(ActivityVO activity) {
		this.activity = activity;
	}

}
