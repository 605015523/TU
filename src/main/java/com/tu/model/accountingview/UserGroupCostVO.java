package com.tu.model.accountingview;

import java.math.BigDecimal;
import java.util.*;

public class UserGroupCostVO implements java.io.Serializable {

	private static final long serialVersionUID = -2829103382200994409L;
	
	private Integer userId;
	private String userName;
	private BigDecimal sum;
	private BigDecimal different;
	private BigDecimal quota;
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

	public BigDecimal getSum() {
		return this.sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public BigDecimal getDifferent() {
		return this.different;
	}

	public void setDifferent(BigDecimal different) {
		this.different = different;
	}

	public Map<Integer, GroupCostVO> getGroupCostVO() {
		return groupCostVO;
	}

	public void setGroupCostVO(Map<Integer, GroupCostVO> groupCostVO) {
		this.groupCostVO = groupCostVO;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

}
