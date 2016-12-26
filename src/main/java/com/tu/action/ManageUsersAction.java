package com.tu.action;

import java.util.List;

import com.tu.model.userlogin.UserloginManageInterface;
import com.tu.model.userlogin.UserloginVO;

public class ManageUsersAction extends AbstractAction {
	
	private static final long serialVersionUID = -4702491501629245694L;
	
	private transient UserloginManageInterface userloginManageBean = null;
	
	private Integer userId;
	private UserloginVO user;
	private List<UserloginVO> users;
	
	public String listAll() {
		users = userloginManageBean.doGetAllUserlogin();
		
		return "list";
	}
	
	public String editUserForm() {
		user = userloginManageBean.dogetOneUserInfoByUserId(userId);
		
		return "editForm";
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

	public void setUsers(List<UserloginVO> users) {
		this.users = users;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserloginVO getUser() {
		return user;
	}

	public void setUser(UserloginVO user) {
		this.user = user;
	}
}
