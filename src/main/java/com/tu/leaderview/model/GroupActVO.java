package com.tu.leaderview.model;

import java.util.*;

public class GroupActVO implements java.io.Serializable {

	private static final long serialVersionUID = 5165359653259615392L;
	
	private Integer actId;
	private Integer groupId;
	private String actName;
	private Float actMoney;
	private String description;
	private String daterange;
	private String actDate;
	private String state;
	private Float sum;
	private Integer participatorNO;
	private List<memberInVO> memberInVO;
	private String comment;

	// Constructors
	/** default constructor */
	public GroupActVO() {
		actId = null;
		groupId = null;
		actName = null;
		actMoney = null;
		description = null;
		daterange = null;
		actDate = null;
		state = null;
		sum = null;
		participatorNO = null;
		memberInVO = null;
		comment = null;
	}

	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public Float getActMoney() {
		return this.actMoney;
	}

	public void setActMoney(Float actMoney) {
		this.actMoney = actMoney;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActDate() {
		return this.actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Float getSum() {
		return this.sum;
	}

	public void setSum(Float sum) {
		this.sum = sum;
	}

	public String getDaterange() {
		return daterange;
	}

	public void setDaterange(String daterange) {
		this.daterange = daterange;
	}

	public List<memberInVO> getMemberInVO() {
		return memberInVO;
	}

	public void setMemberInVO(List<memberInVO> memberInVO) {
		this.memberInVO = memberInVO;
	}

	public Integer getParticipatorNO() {
		return participatorNO;
	}

	public void setParticipatorNO(Integer participatorNO) {
		this.participatorNO = participatorNO;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
