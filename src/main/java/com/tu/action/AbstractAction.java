package com.tu.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.opensymphony.xwork2.ActionSupport;
import com.tu.model.userlogin.UserloginManageInterface;
import com.tu.model.userlogin.UserloginVO;

public abstract class AbstractAction extends ActionSupport {

	private static final long serialVersionUID = 3551374215867513150L;
	
	protected transient UserloginManageInterface userloginManageBean = null;
	
	protected transient HttpServletRequest request = null;
	protected transient HttpServletResponse response = null;
	protected transient HttpSession session = null;

	public void initServletContextObject() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
	}
	
	public UserloginVO getCurrentUser() {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserloginVO userVO = (UserloginVO) session.getAttribute("user");
		if (userVO == null) {
			userVO = userloginManageBean.dogetOneUserInfoByUserName(user.getUsername());
			session.setAttribute("user", userVO);
		}
		
		return userVO;
		
	}
	
	public UserloginManageInterface getUserloginManageBean() {
		return userloginManageBean;
	}

	public void setUserloginManageBean(
			UserloginManageInterface userloginManageBean) {
		this.userloginManageBean = userloginManageBean;
	}
	
	public Map<Integer, Role> getRoles() {
		return Role.ROLES;
	}
}
