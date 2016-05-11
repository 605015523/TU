package leaderview.model;

public class Member_InfoVO implements java.io.Serializable {
	// Filed
	private Integer userId;
	private String userName;
	private String userDept;

	public Member_InfoVO() {

		userId = null;
		userName = null;
		userDept = null;

	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDept() {
		return this.userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

}
