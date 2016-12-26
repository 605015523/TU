package com.tu.action;

import java.util.Date;
import java.util.List;

import com.tu.model.userlogin.UserloginManageInterface;
import com.tu.model.userlogin.UserloginVO;

public class ManageUsersAction extends AbstractAction {
	
	private static final long serialVersionUID = -4702491501629245694L;
	
	private transient UserloginManageInterface userloginManageBean = null;
	
	private Integer userId;
	private UserloginVO user;
	private List<UserloginVO> users;
	
	private String userName;
	private String password;
	private String repassword;
	private String dept;
	private Date inDate;
	private Float quota;
	private Integer role;
	
	public String listAll() {
		users = userloginManageBean.doGetAllUserlogin();
		
		return "list";
	}
	
	public String editUserForm() {
		user = userloginManageBean.dogetOneUserInfoByUserId(userId);
		
		return "editForm";
	}
	
	public String createUser() {
		if (!password.equals(repassword)) {
			return "error";
		}
			
		UserloginVO oneUserVO = new UserloginVO();
		oneUserVO.setUserName(userName);
		oneUserVO.setUserPassword(password);
		oneUserVO.setUserDept(dept);
		oneUserVO.setInDate(inDate);
		oneUserVO.setQuota(quota);
		oneUserVO.setUserRole(role);
		
		userloginManageBean.doCreateUser(oneUserVO);
		return "succcess";
	}
	
	public String addUserForm() {
		return "addForm";
	}

	public UserloginManageInterface getUserloginManageBean() {
		return userloginManageBean;
	}

	public void setUserloginManageBean(UserloginManageInterface userloginManageBean) {
		this.userloginManageBean = userloginManageBean;
	}

	public List<UserloginVO> getUsers() {
		return users;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserloginVO getUser() {
		return user;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	
	public void setQuota(Float quota) {
		this.quota = quota;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}
