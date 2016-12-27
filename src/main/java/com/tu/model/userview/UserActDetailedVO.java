package com.tu.model.userview;

import com.tu.model.activities.ActivityVO;
import com.tu.model.user.act.UserActVO;

public class UserActDetailedVO extends UserActVO {
	private static final long serialVersionUID = -1608977902835248916L;
	
	private ActivityVO activity;

	public UserActDetailedVO() {
		super();
		activity = null;
	}

	public ActivityVO getActivity() {
		return activity;
	}

	public void setActivity(ActivityVO activity) {
		this.activity = activity;
	}

}
