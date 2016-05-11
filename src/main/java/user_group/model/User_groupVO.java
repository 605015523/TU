package user_group.model;

public class User_groupVO implements java.io.Serializable {

	// Filed
	private Integer userId;
	private Integer groupId;

	public User_groupVO() {
		userId = null;
		groupId = null;
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
