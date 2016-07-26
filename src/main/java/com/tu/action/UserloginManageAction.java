package com.tu.action;

import java.util.*;

import com.tu.model.activities.ActivitiesConstant;
import com.tu.model.activities.ActivitiesInterface;
import com.tu.model.activities.ActivityVO;
import com.tu.model.group.GroupInterface;
import com.tu.model.group.GroupVO;
import com.tu.model.messages.MessagesInterface;
import com.tu.model.user.msg.UserMsgConstants;
import com.tu.model.user.msg.UserMsgInterface;
import com.tu.model.userlogin.UserloginVO;
import com.tu.model.userview.UserMsgVO;
import com.tu.model.userview.UserviewInterface;
import com.tu.model.userview.UserviewVO;
import com.tu.util.ConfConstants;

public class UserloginManageAction extends AbstractAction {
	private static final long serialVersionUID = -5768511845633999130L;
	
	private transient UserviewInterface userviewBean = null;
	private transient ActivitiesInterface actsBean = null;
	private transient GroupInterface groupBean = null;
	private transient MessagesInterface msgBean = null;
	private transient UserMsgInterface userMsgBean = null;

	private Integer userId;
	private String userName;
	private Integer userRole;
	private String loginMessage;

	public UserloginManageAction() {
		// do nothing
	}

	public void initUserlogin() {
		userId = null;
		userName = null;
		userRole = null;
	}

	public UserviewInterface getUserviewBean() {
		return this.userviewBean;
	}

	public void setUserviewBean(UserviewInterface userviewBean) {
		this.userviewBean = userviewBean;
	}

	public void setActsBean(ActivitiesInterface actsBean) {
		this.actsBean = actsBean;
	}

	public GroupInterface getGroupBean() {
		return this.groupBean;
	}

	public void setGroupBean(GroupInterface groupBean) {
		this.groupBean = groupBean;
	}

	public UserMsgInterface getUserMsgBean() {
		return this.userMsgBean;
	}

	public void setUserMsgBean(UserMsgInterface userMsgBean) {
		this.userMsgBean = userMsgBean;
	}

	public MessagesInterface getMsgBean() {
		return msgBean;
	}

	public void setMsgBean(MessagesInterface msgBean) {
		this.msgBean = msgBean;
	}

	// The action will be called as user login
	public String doLogin() {

		// User login checking module
		initServletContextObject();
		UserloginVO userLogin = getCurrentUser();
		
		userName = userLogin.getUserName();
		userId = userLogin.getUserId();
		userRole = userLogin.getUserRole();

		LOG.info("login success");

		// To retrieve all the activities' years for displaying by year in front page
		List<ActivityVO> actVOs = actsBean.doGetAllActivity();
		List<Integer> years = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		
		for (ActivityVO actVO : actVOs) {
			cal.setTime(actVO.getActDate());
			int actVOYear = cal.get(Calendar.YEAR);
			
			if (!years.contains(actVOYear)) {
				years.add(actVOYear);
				LOG.info("the year " + actVOYear + " get success");
			}
		}
		
		// 登录成功，判断预算是否该修改，若为当年的2月25号，预算在原基础上加1000
		// Login succeeds, judge the budget by date.
		// if the date after this year, 25th feb., the budget will increase by 1000
		UserloginVO thisuser = userloginManageBean.dogetOneUserInfoByUserId(userId);
		Date date = new Date();// Get the current date
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), ConfConstants.BUDGET_MONTH-1, ConfConstants.BUDGET_DAY, 0, 0);
		Date updatedate = cal.getTime();
		// FIXME float comparison??
		if ((thisuser.getInDate().getTime() - updatedate.getTime() < 0)
				&& updatedate.before(date)
				&& thisuser.getQuota() == ConfConstants.BUDGET_PER_YEAR) {
			thisuser.setQuota(thisuser.getQuota() + ConfConstants.BUDGET_PER_YEAR);
			userloginManageBean.doUpdateOneUserInfo(thisuser);
		}

		// Login succeeds, identify the expiry message and retrieve the number of new messages.
		for (ActivityVO oneAct : actVOs) {
			if (oneAct.getState().equals(ActivitiesConstant.STATE_PUBLISH)) {
				cal.setTime(date);
				cal.add(Calendar.DATE, -1);// 因为actdate中记录的时间是当天的零点,所以对比过期时间时将今天的日期往后推一天
										  // actdate records the 00:00 of current day, so the expiry date will be the day after today
				date = cal.getTime(); // the date is the day after today
				if (oneAct.getActDate().before(date)) {
					oneAct.setState(ActivitiesConstant.STATE_PENDING);
					actsBean.doUpdateOneAct(oneAct);
				}
			}
		}

		int newMsg = 0;
		List<UserMsgVO> userMsgVOs = userMsgBean.doGetUserMsgs(userId);
		for (UserMsgVO userMsgVO : userMsgVOs) {
			
			if (userMsgVO.getState().equals(ActivitiesConstant.STATE_PENDING)
					|| userMsgVO.getState().equals(ActivitiesConstant.STATE_TOBEVALIDATE)
					|| userMsgVO.getState().equals(ActivitiesConstant.STATE_VALIDATE)) {
				userMsgVO.setReadState(UserMsgConstants.STATE_READ);
				userMsgBean.doUpdateOneUserMsg(userMsgVO);

			}
			if (userMsgVO.getReadState().equals(UserMsgConstants.STATE_NEW)) {
				newMsg += 1;
			}
		}

		// Login succeeds, get the User info
		UserviewVO userviewVO = userviewBean.doGetOneUserviewInfoByUserId(this
				.getUserId());

		// Save the following values into session for further disposal
		session.setAttribute("userRole", userRole);
		session.setAttribute("userview", userviewVO);
		session.setAttribute("years", years);
		session.setAttribute("newMsg", newMsg);

		// If the role is group leader
		if (userRole.equals(1)) {

			// Get group info
			GroupVO group = groupBean.doGetOneGroup(userId);

			// Save group info into session
			session.setAttribute("group", group);
		}

		// If the role is approval 
		if (userRole.equals(2)) {
			int newCheck = 0;
			for (int k = 0; k < actVOs.size(); k++) {
				if (actVOs.get(k).getState().equals(ActivitiesConstant.STATE_DRAFT)
						|| actVOs.get(k).getState().equals(ActivitiesConstant.STATE_TOBEVALIDATE)) {
					newCheck += 1;
				}
			}

			List<String> groupsName = new ArrayList<String>();
			List<GroupVO> allgroup = groupBean.doGetAllGroup();
			for (int i = 0; i < allgroup.size(); i++) {
				groupsName.add(allgroup.get(i).getGroupName());
			}
			session.setAttribute("newCheck", newCheck);
			session.setAttribute("groupsName", groupsName);
		}

		return "redirectToHomePage";
	}
	
	public String displayHomePage() {
		return "homePage";
	}
	
	public String displayLoginFailed() {
		// Login failed, the following will run
		initServletContextObject();
		LOG.info("login fail");
		setLoginMessage("Incorrect ID or password. Please re-enter.");
		return "loginFail";
	}
	

	// getter, setters
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

	public Integer getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public String getLoginMessage() {
		return loginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}

}
