package com.tu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractAction extends ActionSupport {

	private static final long serialVersionUID = 3551374215867513150L;
	
	protected transient HttpServletRequest request = null;
	protected transient HttpServletResponse response = null;
	protected transient HttpSession session = null;

	public void initServletContextObject() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
	}
}
