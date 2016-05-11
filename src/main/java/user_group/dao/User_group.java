package user_group.dao;

public class User_group implements java.io.Serializable {

	// Filed
	private Integer userId;
	private Integer groupId;

	public User_group() {
	}

	public User_group(Integer userId, Integer groupId) {
		this.groupId = groupId;
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
