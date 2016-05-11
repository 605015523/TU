package user_msg.dao;

public class User_msg implements java.io.Serializable {

	private Integer userId;
	private Integer msgId;
	private String readState;

	public User_msg() {

	}

	public User_msg(Integer userId, Integer msgId, String readState) {
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
