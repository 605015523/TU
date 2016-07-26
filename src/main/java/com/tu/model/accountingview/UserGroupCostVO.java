package com.tu.model.accountingview;

import java.util.*;

public class UserGroupCostVO implements java.io.Serializable {

	private static final long serialVersionUID = -2829103382200994409L;
	
	private Integer userId;
	private String userName;
	private Float sum;
	private Float different;
	private Float quota;
	private Map<Integer, GroupCostVO> groupCostVO;

	// Constructors
	/** default constructor */
	public UserGroupCostVO() {
		userId = null;
		userName = null;
		sum = null;
		quota = null;
		different = null;
		groupCostVO = null;
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

	public Float getSum() {
		return this.sum;
	}

	public void setSum(Float sum) {
		this.sum = sum;
	}

	public Float getDifferent() {
		return this.different;
	}

	public void setDifferent(Float different) {
		this.different = different;
	}

	public Map<Integer, GroupCostVO> getGroupCostVO() {
		return groupCostVO;
	}

	public void setGroupCostVO(Map<Integer, GroupCostVO> groupCostVO) {
		this.groupCostVO = groupCostVO;
	}

	public Float getQuota() {
		return quota;
	}

	public void setQuota(Float quota) {
		this.quota = quota;
	}

}
