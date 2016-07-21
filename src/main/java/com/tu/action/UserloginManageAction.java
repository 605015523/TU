package com.tu.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.opensymphony.xwork2.ActionSupport;
import com.tu.activities.model.ActivitiesConstant;
import com.tu.activities.model.ActivitiesInterface;
import com.tu.activities.model.ActivitiesVO;
import com.tu.dao.user.msg.UserMsg;
import com.tu.group.model.GroupInterface;
import com.tu.group.model.GroupVO;
import com.tu.leaderview.model.LeaderviewInterface;
import com.tu.messages.model.MessagesInterface;
import com.tu.messages.model.MessagesVO;
import com.tu.user.group.model.UserGroupInterface;
import com.tu.user.msg.model.UserMsgInterface;
import com.tu.userlogin.model.UserloginManageInterface;
import com.tu.userlogin.model.UserloginVO;
import com.tu.userview.model.UserviewInterface;
import com.tu.userview.model.UserviewVO;

public class UserloginManageAction extends ActionSupport {
	private static final long serialVersionUID = -5768511845633999130L;
	
	private transient HttpServletRequest request = null;
	private transient HttpServletResponse response = null;
	private transient HttpSession session = null;

	private transient UserloginManageInterface userloginManageBean = null;
	private transient UserviewInterface userviewBean = null;
	private transient LeaderviewInterface leaderviewBean = null;
	private transient ActivitiesInterface actsBean = null;
	private transient GroupInterface groupBean = null;
	private transient MessagesInterface msgBean = null;
	private transient UserMsgInterface userMsgBean = null;
	private transient UserGroupInterface userGroupBean = null;

	private Integer userId;
	private String userName;
	private Integer userRole;
	private List<String> groupName;
	private String userDept;
	private Date inDate;
	private Date outDate;
	private Float old_balance;
	private Float quota;
	private Float remaining;
	private String loginMessage;

	public UserloginManageAction() {
	}

	public void initUserlogin() {
		userId = null;
		userName = null;
		userRole = null;
		userDept = null;
		inDate = null;
		outDate = null;
		old_balance = null;
		quota = null;
	}

	public void initServletContextObject() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
	}

	public void setUserloginManageBean(
			UserloginManageInterface userloginManageBean) {
		this.userloginManageBean = userloginManageBean;
	}

	public UserviewInterface getUserviewBean() {
		return this.userviewBean;
	}

	public void setUserviewBean(UserviewInterface userviewBean) {
		this.userviewBean = userviewBean;
	}

	public LeaderviewInterface getLeaderviewBean() {
		return leaderviewBean;
	}

	public void setLeaderviewBean(LeaderviewInterface leaderviewBean) {
		this.leaderviewBean = leaderviewBean;
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

	public UserGroupInterface getUserGroupBean() {
		return userGroupBean;
	}

	public void setUserGroupBean(UserGroupInterface userGroupBean) {
		this.userGroupBean = userGroupBean;
	}

	// 用户登录调用action
	public String doLogin() throws ParseException {

		// 用户登录验证模块
		initServletContextObject();
		List<UserloginVO> knowledgeadministratorVOs = userloginManageBean.doGetAllUserlogin();
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		for (int i = 0; i < knowledgeadministratorVOs.size(); i++) {
			if (user.getUsername().equals(knowledgeadministratorVOs.get(i).getUserName())) {
				userName = knowledgeadministratorVOs.get(i).getUserName();
				userId = knowledgeadministratorVOs.get(i).getUserId();
				userRole = knowledgeadministratorVOs.get(i).getUserRole();
				break;
			}
		}

		// 登录成功，输出到控制台
		LOG.info("login success");

		// 登录成功，获取所有数据库中存放的活动年限，以便登录后首页的活动下拉菜单选取年限
		List<ActivitiesVO> actVOs = actsBean.doGetAllActivity();
		List<Integer> years = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		
		for (ActivitiesVO actVO : actVOs) {
			cal.setTime(actVO.getActDate());
			int actVOYear = cal.get(Calendar.YEAR);
			
			if (!years.contains(actVOYear)) {
				years.add(actVOYear);
				LOG.info("the year " + actVOYear + " get success");
			}
		}
		
		// 登录成功，判断预算是否该修改，若为当年的7月1号，预算在原基础上加1000
		UserloginVO thisuser = userloginManageBean
				.dogetOneUserInfoByUserId(userId);
		Date date = new Date();// 取当前时间
		cal.setTime(date);
		String year = Integer.toString(cal.get(Calendar.YEAR));
		Date updatedate = new SimpleDateFormat("yyyy-MM-dd").parse(year
				+ "-02-25");
		try {
			if ((thisuser.getInDate().getTime() - updatedate.getTime() < 0)
					&& updatedate.before(date)
					&& thisuser.getQuota() == 1000) {
				thisuser.setQuota(thisuser.getQuota() + 1000);
				userloginManageBean.doUpdateOneUserInfo(thisuser);
			}
		} catch (Exception e) {

		}

		// 登录成功，识别过期消息，并获取新消息条数
		for (ActivitiesVO oneAct : actVOs) {
			if (oneAct.getState().equals(ActivitiesConstant.STATE_PUBLISH)) {
				cal.setTime(date);
				cal.add(Calendar.DATE, -1);// 因为actdate中记录的时间是当天的零点
												// 所以对比过期时间时将今天的日期往后推一天
				date = cal.getTime(); // 这个时间就是日期往后推一天的结果
				if (oneAct.getActDate().before(date)) {
					oneAct.setState(ActivitiesConstant.STATE_PENDING);
					actsBean.doUpdateOneAct(oneAct);
				}
			}
		}

		int newMsg = 0;
		List<UserMsg> userMsgVOs = userMsgBean.doGetUserMsg(userId);
		for (UserMsg userMsgVO : userMsgVOs) {
			MessagesVO oneMsg = msgBean.doGetOneMsgById(userMsgVO
					.getMsgId());
			ActivitiesVO oneAct = actsBean.doGetOneActById(oneMsg.getActId());
			
			if (oneAct.getState().equals(ActivitiesConstant.STATE_PENDING)
					|| oneAct.getState().equals(ActivitiesConstant.STATE_TOBEVALIDATE)
					|| oneAct.getState().equals(ActivitiesConstant.STATE_VALIDATE)) {
				userMsgVO.setReadState("read");
				userMsgBean.doUpdateOneUserMsg(userMsgVO);

			}
			if (userMsgVO.getReadState().equals("new")) {
				newMsg += 1;
			}
		}

		// 登录成功，获取用户个人信息bean
		UserviewVO userviewVO = userviewBean.doGetOneUserviewInfoByUserId(this
				.getUserId());

		// 登陆后，将用户一直需要用到的这些信息存到session，以便后面页面的使用
		session.setAttribute("userId", userId);
		session.setAttribute("userRole", userRole);
		session.setAttribute("userview", userviewVO);
		session.setAttribute("years", years);
		session.setAttribute("newMsg", newMsg);

		// 登陆成功，若为组长执行以下活动
		if (userRole.equals(1)) {

			// 获取所属小组信息，以便后面的使用
			GroupVO group = groupBean.dogetOneGroup(userId);

			// 获取所有用户Id，以便添加活动时发送消息给用户
			List<Integer> allMemberId = new ArrayList<Integer>();
			for (int m = 0; m < knowledgeadministratorVOs.size(); m++) {
				Integer userId = knowledgeadministratorVOs.get(m).getUserId();
				allMemberId.add(userId);
			}

			// 将刚刚获取到的内容存到session中
			session.setAttribute("group", group);
			session.setAttribute("allMemberId", allMemberId);
		}

		// 若为审批小组成员，执行以下操作
		if (userRole.equals(2)) {
			int newCheck = 0;
			for (int k = 0; k < actVOs.size(); k++) {
				if (actVOs.get(k).getState().equals(ActivitiesConstant.STATE_DRAFT)
						|| actVOs.get(k).getState().equals(ActivitiesConstant.STATE_TOBEVALIDATE)) {
					newCheck += 1;
				}
			}
			List<Integer> allUserId = new ArrayList<Integer>();
			for (int i = 0; i < knowledgeadministratorVOs.size(); i++) {
				allUserId.add(knowledgeadministratorVOs.get(i).getUserId());
			}

			List<String> groupsName = new ArrayList<String>();
			List<GroupVO> allgroup = groupBean.dogetAllGroup();
			for (int i = 0; i < allgroup.size(); i++) {
				groupsName.add(allgroup.get(i).getGroupName());
			}
			session.setAttribute("allserId", allUserId);
			session.setAttribute("newCheck", newCheck);
			session.setAttribute("groupsName", groupsName);
		}

		return "userloginSuccess";
	}
	
	public String displayLoginFailed() {
		// 登录不成功所执行的操作
		initServletContextObject();
		LOG.info("login fail");
		loginMessage = "Incorrect ID or password. Please re-enter.";
		request.setAttribute("loginMessage", loginMessage);
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

	public String getUserDept() {
		return this.userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public Date getInDate() {
		return this.inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return this.outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Float getOld_balance() {
		return this.old_balance;
	}

	public void setOld_balance(Float oldBalance) {
		this.old_balance = oldBalance;
	}

	public Float getQuota() {
		return this.quota;
	}

	public void setQuota(Float quota) {
		this.quota = quota;
	}

	public List<String> getGroupName() {
		return this.groupName;
	}

	public void setGroupName(List<String> groupName) {
		this.groupName = groupName;
	}

	public Float getRemaining() {
		return this.remaining;
	}

	public void setRemaining(Float remaining) {
		this.remaining = remaining;
	}

}
