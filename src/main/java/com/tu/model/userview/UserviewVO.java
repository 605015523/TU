package com.tu.model.userview;

import java.math.BigDecimal;

import com.tu.model.userlogin.UserloginVO;

public class UserviewVO implements java.io.Serializable {
	private static final long serialVersionUID = -3537565081639531832L;
	
	// Fields
	private UserloginVO user;
	private BigDecimal remaining;

	public UserviewVO() {

		setUser(null);
		remaining = null;
	}

	public BigDecimal getRemaining() {
		return this.remaining;
	}

	public void setRemaining(BigDecimal remaining) {
		this.remaining = remaining;
	}

	public UserloginVO getUser() {
		return user;
	}

	public void setUser(UserloginVO user) {
		this.user = user;
	}

}
