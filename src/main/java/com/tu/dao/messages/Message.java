package com.tu.dao.messages;


public class Message implements java.io.Serializable {
	private static final long serialVersionUID = -2301807989217269139L;
	
	// Fields
	private Integer msgId;
	private Integer actId;

	// Constructors
	/** default constructor */
	public Message() {
		// do nothing
	}

	/** full constructor */
	public Message(Integer msgId, Integer actId) {
		this.msgId = msgId;
		this.actId = actId;
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

}
