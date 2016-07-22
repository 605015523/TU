package com.tu.action;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.model.accountingview.AccountingviewInterface;
import com.tu.model.accountingview.UserGroupCostVO;
import com.tu.model.activities.ActivitiesConstant;
import com.tu.model.activities.ActivitiesInterface;
import com.tu.model.activities.ActivitiesVO;
import com.tu.model.group.GroupInterface;
import com.tu.model.group.GroupVO;
import com.tu.model.leaderview.GroupActVO;
import com.tu.model.userlogin.UserloginManageInterface;
import com.tu.model.userlogin.UserloginVO;

public class AccountingviewAction extends AbstractAction {
	private static final long serialVersionUID = 5474650091305219468L;
	private static final Log LOGGER = LogFactory.getLog(AccountingviewAction.class);
	
	private transient AccountingviewInterface accountingviewBean = null;
	private transient ActivitiesInterface actsBean = null;
	private transient GroupInterface groupBean = null;
	private transient UserloginManageInterface userloginManageBean = null;

	// To receive the value from the web page control and return to the parameter of the "success" web page
	private String checkState;
	private String comment;
	
	// To display a group activity
	private GroupActVO groupAct;

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

	public UserloginManageInterface getUserloginManageBean() {
		return this.userloginManageBean;
	}

	public void setUserloginManageBean(
			UserloginManageInterface userloginManageBean) {
		this.userloginManageBean = userloginManageBean;
	}

	// To retrieve all the activities which are needed to be approval. 
	public String doGetAllCheckAct() {
		initServletContextObject();
		List<GroupActVO> actsVO = accountingviewBean.doGetAllCheckValidateActs();
		LOGGER.info("the doGetAllGroupAct get success");
		session.setAttribute("acts", actsVO);

		return "doGetAllAct";
	}

	// To retrieve all the detail of one specific activity which is in check.  
	public String doshowCheckDetails() {
		initServletContextObject();
		int oneactId = Integer.parseInt(request.getParameter("actId"));
		groupAct = accountingviewBean.doGetAllValidateDetails(oneactId);

		return "ShowCheckDetails";
	}

	// To retrieve all the activities' details which are needed to be validated.
	public String doshowValidateDetails() {
		initServletContextObject();
		int oneactId = Integer.parseInt(request.getParameter("actId"));
		groupAct = accountingviewBean.doGetAllValidateDetails(oneactId);

		return "ShowValidateDetails";
	}

	// To check current activity, then modify the activity's status valued from "approved" or "disapproval" .
	public String doCheckAct() {
		initServletContextObject();
		int oneactId = (Integer) session.getAttribute("checkedActId");
		ActivitiesVO checkAct = actsBean.doGetOneActById(oneactId);
		checkAct.setState(checkState);
		checkAct.setComment(comment);
		actsBean.doUpdateOneAct(checkAct);

		// Update the number of news which are needed to be checked or validated 
		int newCheck = getNbActsToCheck();
		session.setAttribute("newCheck", newCheck);

		// Update the web page to display all the activities  
		List<GroupActVO> actsVO = accountingviewBean.doGetAllCheckValidateActs();
		session.setAttribute("acts", actsVO);
		return "doGetAllAct";

	}
	
	private Integer getNbActsToCheck() {
		Integer newCheck = 0;
		List<ActivitiesVO> allActs = actsBean.doGetAllActivity();
		for (int i = 0; i < allActs.size(); i++) {
			if (allActs.get(i).getState().equals(ActivitiesConstant.STATE_DRAFT)
					|| allActs.get(i).getState().equals(ActivitiesConstant.STATE_TOBEVALIDATE)) {
				newCheck += 1;
			}
		}
		
		return newCheck;
	}

	
	// To validate the current activity with its status changing from "tobevalidate" to "validate"
	// Then, re-calculate user's spending
	
	public String doValidateAct() {
		initServletContextObject();
		int oneactId = (Integer) session.getAttribute("validateActId");
		ActivitiesVO validateAct = actsBean.doGetOneActById(oneactId);
		validateAct.setState(ActivitiesConstant.STATE_VALIDATE);
		validateAct.setComment(getComment());
		actsBean.doUpdateOneAct(validateAct);

		// re-calculate user's spending
		GroupActVO groupactVO = accountingviewBean.doGetAllValidateDetails(oneactId);
		for (int i = 0; i < groupactVO.getMemberInVO().size(); i++) {
			Integer userId = groupactVO.getMemberInVO().get(i).getUserId();
			Float spending = groupactVO.getMemberInVO().get(i).getConsumption();
			UserloginVO oneUserVO = userloginManageBean.dogetOneUserInfoByUserId(userId);
			oneUserVO.setSpending(oneUserVO.getSpending() + spending);
			userloginManageBean.doUpdateOneUserInfo(oneUserVO);
		}

		// Update the number of news needed to be checked and validated.
		int newCheck = getNbActsToCheck();
		session.setAttribute("newCheck", newCheck);

		// Refresh the web page which displays all activities.
		List<GroupActVO> actsVO = accountingviewBean.doGetAllCheckValidateActs();
		session.setAttribute("acts", actsVO);
		return "doGetAllAct";
	}

	// Display all users' activities by year ( activity shall be in validated status)
	public String doshowAllActsByYear() {
		initServletContextObject();
		Integer year = Integer.parseInt(request.getParameter("year"));
		List<UserGroupCostVO> allUserGroupCostVO = accountingviewBean.doGetAllActsByYear(year);
		session.setAttribute("allUserGroupCost", allUserGroupCostVO);

		return "doShowActByYear";
	}

	// Display the chosen group's activities by groupname (activity shall be in validated status)
	public String doshowAllActsByGroup() {
		initServletContextObject();
		String groupName = request.getParameter("groupname");
		GroupVO group = groupBean.dogetOneGroupByName(groupName);
		List<GroupActVO> groupactsVO = accountingviewBean.doGetAllActsByGroupId(group
				.getGroupId());
		session.setAttribute("groupacts", groupactsVO);

		return "doShowActByGroup";

	}

	// Display activity detail of group
	public String doshowActDetailsInGroup() {
		initServletContextObject();
		int oneactId = Integer.parseInt(request.getParameter("actId"));
		groupAct = accountingviewBean.doGetAllValidateDetails(oneactId);

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

}
