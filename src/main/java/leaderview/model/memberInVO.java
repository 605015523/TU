package leaderview.model;

public class memberInVO implements java.io.Serializable {

	private Integer userId;
	private String userName;
	private String userDept;
	private Integer participatorNO;
	private Float consumption;
	private String remark;

	public memberInVO() {
	}

	public memberInVO(Integer userId, String userName, String userDept,
			Integer participatorNO, Float consumption, String remark) {
		this.userId = userId;
		this.userName = userName;
		this.userDept = userDept;
		this.participatorNO = participatorNO;
		this.consumption = consumption;
		this.remark = remark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getParticipatorNO() {
		return participatorNO;
	}

	public void setParticipatorNO(Integer participatorNO) {
		this.participatorNO = participatorNO;
	}

	public Float getConsumption() {
		return consumption;
	}

	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

}
