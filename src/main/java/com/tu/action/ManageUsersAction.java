package com.tu.action;

import java.math.BigDecimal;
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
	private Date outDate;
	private BigDecimal quota;
	private Integer role;
	
	public String listAll() {
		initServletContextObject();
		users = userloginManageBean.doGetAllUserlogin();
		
		return "list";
	}
	
	public String editUserForm() {
		initServletContextObject();
		user = userloginManageBean.dogetOneUserInfoByUserId(userId);
		
		return "editForm";
	}
	
	public String createUser() {
		initServletContextObject();
		if (!password.equals(repassword)) {
			return "error";
		}
			
		UserloginVO userVO = new UserloginVO();
		userVO.setUserId(userId);
		userVO.setUserName(userName);
		userVO.setUserPassword(password);
		userVO.setUserDept(dept);
		userVO.setInDate(inDate);
		userVO.setQuota(quota);
		userVO.setUserRole(role);
		
		userloginManageBean.doCreateUser(userVO);
		return "succcess";
	}
	
	public String updateUser() {
		initServletContextObject();
		
		UserloginVO userVO = userloginManageBean.dogetOneUserInfoByUserId(userId);
		userVO.setUserDept(dept);
		userVO.setInDate(inDate);
		userVO.setOutDate(outDate);
		userVO.setQuota(quota);
		userVO.setUserRole(role);
		
		userloginManageBean.doUpdateOneUserInfo(userVO);
		
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
	
	public Integer getUserId() {
		return userId;
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
	
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	
	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}
