package com.tu.action;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.model.accountingview.AccountingviewInterface;
import com.tu.model.accountingview.UserGroupCostVO;
import com.tu.model.activities.ActivitiesConstant;
import com.tu.model.activities.ActivitiesInterface;
import com.tu.model.activities.ActivityVO;
import com.tu.model.group.GroupInterface;
import com.tu.model.group.GroupVO;
import com.tu.model.leaderview.GroupActVO;
import com.tu.model.leaderview.MemberInVO;
import com.tu.model.userlogin.UserloginVO;

public class AccountingviewAction extends AbstractAction {
	private static final long serialVersionUID = 5474650091305219468L;
	private static final Log LOGGER = LogFactory.getLog(AccountingviewAction.class);
	
	private transient AccountingviewInterface accountingviewBean = null;
	private transient ActivitiesInterface actsBean = null;
	private transient GroupInterface groupBean = null;

	// To receive the value from the web page control and return to the parameter of the "success" web page
	private Integer actId;
	private Integer year;
	private String checkState;
	private String comment;
	
	// To display
	private GroupActVO groupAct;
	private List<GroupActVO> groupActs;
	private List<UserGroupCostVO> allUserGroupCost;

	public AccountingviewAction() {
		// do nothing
	}

	public ActivitiesInterface getActsBean() {
		return this.actsBean;
	}

	public void setActsBean(ActivitiesInterface actsBean) {
		this.actsBean = actsBean;
	}

	public AccountingviewInterface getAccountingviewBean() {
		return this.accountingviewBean;
	}

	public void setAccountingviewBean(AccountingviewInterface accountingviewBean) {
		this.accountingviewBean = accountingviewBean;
	}

	public GroupInterface getGroupBean() {
		return this.groupBean;
	}

	public void setGroupBean(GroupInterface groupBean) {
		this.groupBean = groupBean;
	}

	// To retrieve all the activities which are needed to be approval. 
	public String doGetAllCheckAct() {
		groupActs = accountingviewBean.doGetAllCheckValidateActs();
		LOGGER.info("the doGetAllGroupAct get success");
	
		return "doGetAllAct";
	}

	// To retrieve all the detail of one specific activity which is in check.  
	public String doshowCheckDetails() {
		groupAct = accountingviewBean.doGetGroupActivityByID(actId);

		return "ShowCheckDetails";
	}

	// To retrieve all the activities' details which are needed to be validated.
	public String doshowValidateDetails() {
		groupAct = accountingviewBean.doGetGroupActivityByID(actId);

		return "ShowValidateDetails";
	}

	// To check current activity, then modify the activity's status valued from "approved" or "disapproval" .
	public String doCheckAct() {
		initServletContextObject();
		ActivityVO checkAct = actsBean.doGetOneActById(actId);
		checkAct.setState(checkState);
		checkAct.setComment(comment);
		actsBean.doUpdateOneAct(checkAct);

		// Update the number of news which are needed to be checked or validated 
		int newCheck = getNbActsToCheck();
		session.setAttribute("newCheck", newCheck);

		return "redirectDoGetAllAct";

	}
	
	private Integer getNbActsToCheck() {
		Integer newCheck = 0;
		List<ActivityVO> allActs = actsBean.doGetAllActivity();
		for (ActivityVO act : allActs) {
			if (act.getState().equals(ActivitiesConstant.STATE_DRAFT)
					|| act.getState().equals(ActivitiesConstant.STATE_TOBEVALIDATE)) {
				newCheck++;
			}
		}
		
		return newCheck;
	}

	
	// To validate the current activity with its status changing from "tobevalidate" to "validate"
	// Then, re-calculate user's spending
	
	public String doValidateAct() {
		initServletContextObject();
		ActivityVO validateAct = actsBean.doGetOneActById(actId);
		validateAct.setState(ActivitiesConstant.STATE_VALIDATE);
		validateAct.setComment(getComment());
		actsBean.doUpdateOneAct(validateAct);

		// re-calculate user's spending
		GroupActVO groupactVO = accountingviewBean.doGetGroupActivityByID(actId);
		for (MemberInVO memberInVO : groupactVO.getMemberInVO()) {
			Integer userId = memberInVO.getUserId();
			Float spending = memberInVO.getConsumption();
			UserloginVO oneUserVO = userloginManageBean.dogetOneUserInfoByUserId(userId);
			oneUserVO.setSpending(oneUserVO.getSpending() + spending);
			userloginManageBean.doUpdateOneUserInfo(oneUserVO);
		}

		// Update the number of news needed to be checked and validated.
		int newCheck = getNbActsToCheck();
		session.setAttribute("newCheck", newCheck);

		return "redirectDoGetAllAct";
	}

	// Display all users' activities by year ( activity shall be in validated status)
	public String doshowAllActsByYear() {
		allUserGroupCost = accountingviewBean.doGetUserGroupCostsForValidatedActsByYear(year);

		return "doShowActByYear";
	}

	// Display the chosen group's activities by groupname (activity shall be in validated status)
	public String doshowAllActsByGroup() {
		initServletContextObject();
		String groupName = request.getParameter("groupname");
		GroupVO group = groupBean.doGetOneGroupByName(groupName);
		groupActs = accountingviewBean.doGetAllActsByGroupId(group
				.getGroupId());

		return "doShowActByGroup";

	}

	// Display activity detail of group
	public String doshowActDetailsInGroup() {
		groupAct = accountingviewBean.doGetGroupActivityByID(actId);

		return "doShowActByGroupDetails";
	}

	// get() and set() to property
	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public GroupActVO getGroupAct() {
		return groupAct;
	}

	public void setGroupAct(GroupActVO groupAct) {
		this.groupAct = groupAct;
	}

	public List<GroupActVO> getGroupActs() {
		return groupActs;
	}

	public void setGroupActs(List<GroupActVO> groupActs) {
		this.groupActs = groupActs;
	}

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public List<UserGroupCostVO> getAllUserGroupCost() {
		return allUserGroupCost;
	}

	public void setAllUserGroupCostVO(List<UserGroupCostVO> allUserGroupCost) {
		this.allUserGroupCost = allUserGroupCost;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
