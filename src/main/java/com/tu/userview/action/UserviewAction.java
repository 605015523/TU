package com.tu.userview.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tu.activities.model.ActivitiesConstant;
import com.tu.activities.model.ActivitiesInterface;
import com.tu.activities.model.ActivitiesVO;
import com.tu.messages.model.MessagesInterface;
import com.tu.user_act.model.User_actInterface;
import com.tu.user_act.model.User_actVO;
import com.tu.user_msg.dao.User_msg;
import com.tu.user_msg.model.User_msgInterface;
import com.tu.userlogin.model.UserloginManageInterface;
import com.tu.userlogin.model.UserloginVO;
import com.tu.userview.model.User_msgVO;
import com.tu.userview.model.UseractsVO;
import com.tu.userview.model.UserviewInterface;
import com.tu.userview.model.UserviewVO;

public class UserviewAction extends ActionSupport {

	private static final Log LOG = LogFactory.getLog(UserviewAction.class);
	private static final long serialVersionUID = -6352178618288011965L;
	
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private HttpSession session = null;

	private UserloginManageInterface userloginManageBean = null;
	private UserviewInterface userviewBean = null;
	private User_actInterface user_actBean = null;
	private MessagesInterface msgBean = null;
	private User_msgInterface user_msgBean = null;
	private ActivitiesInterface actsBean = null;

	// 接收调用页的相应控件值，正常返回后传给success对应页面的参数
	private Integer userId;
	private String userName;
	private List<String> groupName;
	private String userPassword;
	private String userRole;
	private String userDept;
	private Date in_date;
	private Date out_date;
	private Float spending;
	private Float quota;
	private Float remaining;
	private Integer participatorNO;
	private Float consumption;
	private String description;
	private String remark;

	public UserviewAction() {

	}

	public void setUserloginManageBean(
			UserloginManageInterface UserloginManageBean) {
		this.userloginManageBean = UserloginManageBean;
	}

	public UserviewInterface getUserviewBean() {
		return this.userviewBean;
	}

	public void setUserviewBean(UserviewInterface userviewBean) {
		this.userviewBean = userviewBean;
	}

	public User_actInterface getUser_actBean() {
		return this.user_actBean;
	}

	public void setUser_actBean(User_actInterface userActBean) {
		this.user_actBean = userActBean;
	}

	public UserloginManageInterface getUserloginManageBean() {
		return this.userloginManageBean;
	}

	public MessagesInterface getMsgBean() {
		return this.msgBean;
	}

	public void setMsgBean(MessagesInterface msgBean) {
		this.msgBean = msgBean;
	}

	public User_msgInterface getUser_msgBean() {
		return this.user_msgBean;
	}

	public void setUser_msgBean(User_msgInterface user_msgBean) {
		this.user_msgBean = user_msgBean;
	}

	public ActivitiesInterface getActsBean() {
		return actsBean;
	}

	public void setActsBean(ActivitiesInterface actsBean) {
		this.actsBean = actsBean;
	}

	public void initServletContextObject() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
	}

	// 显示所有参与过的活动
	public String doshowActs() {
		initServletContextObject();
		Integer userId = (Integer) session.getAttribute("userId");
		Integer year = Integer.valueOf(request.getParameter("year"));
		List<UseractsVO> useractsVO = userviewBean.doGetAllUserActsByUserId(userId, year);
		session.setAttribute("acts", useractsVO);
		session.setAttribute("thisyear", year);
		return "showActs";
	}

	// 显示选定的参与过的活动细节
	public String doshowDetails() {
		initServletContextObject();
		List<UseractsVO> Useracts = (List<UseractsVO>) session
				.getAttribute("acts");
		int oneactId = Integer.parseInt(request.getParameter("actId"));
		int year = Integer.parseInt(request.getParameter("actId"));
		for (int i = 0; i < Useracts.size(); i++) {
			int actId = Useracts.get(i).getActId();
			LOG.info("the actId is" + actId);
			LOG.info("the actName is" + Useracts.get(i).getActName());
			if (oneactId == actId) {
				request.setAttribute("act", Useracts.get(i));
			}
		}
		return "showDetails";
	}

	// 显示所有messages
	public String doshowMessages() {
		initServletContextObject();
		Integer userId = (Integer) session.getAttribute("userId");
		List<User_msgVO> messages = userviewBean.dogetMessages(userId);
		List<User_msgVO> overmessages = new ArrayList<User_msgVO>();
		List<User_msgVO> inmessages = new ArrayList<User_msgVO>();

		for (int i = 0; i < messages.size(); i++) {
			ActivitiesVO oneAct = new ActivitiesVO();
			oneAct = actsBean.doGetOneActById(messages.get(i).getActId());

			if (oneAct.getState().equals(ActivitiesConstant.STATE_PUBLISH)) {
				inmessages.add(messages.get(i));
			} else {
				overmessages.add(messages.get(i));
			}
		}
		session.setAttribute("inmessages", inmessages);
		session.setAttribute("overmessages", overmessages);
		return "showMessages";
	}

	// 显示所有messages的细节
	public String doshowMsgDetails() {
		initServletContextObject();
		List<User_msgVO> messages = (List<User_msgVO>) session.getAttribute("inmessages");
		Integer msgId = Integer.parseInt(request.getParameter("msgId"));
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).getMsgId().equals(msgId)) {
				session.setAttribute("msgDetails", messages.get(i));
			}
		}

		// 将User_msg中的状态设置为已读
		User_msg oneUser_Msg = new User_msg();
		Integer userId = (Integer) session.getAttribute("userId");
		oneUser_Msg = user_msgBean.dogetOneByUserIdAndMsgId(userId, msgId);

		if (oneUser_Msg.getReadState().equals("new")) {

			oneUser_Msg.setReadState("read");
			user_msgBean.doUpdateOneUser_msg(oneUser_Msg);
			int newMsg = 0;
			List<User_msg> user_msgVOs = new ArrayList<User_msg>();
			user_msgVOs = (List<User_msg>) user_msgBean
					.doGetUserMsg(userId);
			for (int i = 0; i < user_msgVOs.size(); i++) {

				if (user_msgVOs.get(i).getReadState().equals("new")) {
					newMsg += 1;
				}

			}
			session.setAttribute("newMsg", newMsg);

		}

		return "showMsgDetails";
	}

	// 跳转到参与某个活动的界面
	public String doInAct() {
		initServletContextObject();
		List<User_msgVO> messages = (List<User_msgVO>) session.getAttribute("inmessages");
		Integer msgId = Integer.parseInt(request.getParameter("msgId"));
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).getMsgId().equals(msgId)) {
				session.setAttribute("message", messages.get(i));
				session.setAttribute("inActId", messages.get(i).getActId());
			}
		}
		return "doInAct";
	}

	// 请求参与某个活动具体操作
	public String doActRequest() {
		initServletContextObject();
		String addMessage = null;
		User_actVO oneUser_actVO = new User_actVO();
		Integer actId = (Integer) session.getAttribute("inActId");
		LOG.info("the inActid is:" + actId);
		Integer userId = (Integer) session.getAttribute("userId");
		oneUser_actVO.setActId(actId);
		oneUser_actVO.setUserId(userId);
		oneUser_actVO.setParticipatorNO(getParticipatorNO());
		oneUser_actVO.setConsumption(getConsumption());
		oneUser_actVO.setRemark(getRemark());
		try {
			addMessage = user_actBean.doAddOneUser_act(oneUser_actVO);
		} catch (Exception e) {
			addMessage = "there are something wrong with control layer: "
					+ e.toString();
		}

		initServletContextObject();
		List<Integer> years = (List<Integer>) session.getAttribute("years");
		Integer year = years.get(years.size() - 1);
		LOG.info("the year in action is:" + year);
		LOG.info("the userId in action is:" + userId);
		List<UseractsVO> useractsVO = userviewBean.doGetAllUserActsByUserId(userId, year);

		for (int i = 0; i < useractsVO.size(); i++) {
			LOG.info("the act" + useractsVO.get(i).getActName()
					+ "get success");
		}
		session.setAttribute("acts", useractsVO);
		session.setAttribute("thisyear", year);
		return "showActs";
	}

	// 修改密码
	public String doChangePwd() throws Exception {
		String updateMessage = null;
		initServletContextObject();
		UserloginVO userInfo = new UserloginVO();
		String oldpassword = (String) request.getParameter("oldpassword");
		String newpassword = (String) request.getParameter("newpassword");
		Integer userId = (Integer) session.getAttribute("userId");
		LOG.info("the userId is:" + userId);
		userInfo = userloginManageBean.dogetOneUserInfoByUserId(userId);
		String orgpassword = userInfo.getUserPassword();
		userInfo.setUserPassword(newpassword);
		boolean a = (orgpassword.equals(oldpassword));
		if (a == true) {
			updateMessage = userviewBean.doupdateOneuserInfo(userInfo);
			request.setAttribute("updateMessage", updateMessage);
			return "pwdUpdateSuccess";
		} else {
			updateMessage = "Incorrect old password,please re-enter!";
			request.setAttribute("updateMessage", updateMessage);
			return "pwdUpdateFail";
		}

	}

	// 删除一个想参与的活动（特别的，若该活动报名时间结束则不能再删除）
	public String doDeleteOneAct() throws Exception {
		String actionReturnMessage = null;
		initServletContextObject();
		Integer userId = (Integer) session.getAttribute("userId");
		Integer actId = Integer.valueOf(request.getParameter("actId"));
		try {
			actionReturnMessage = user_actBean.doDeleteOneUser_act(userId,
					actId);
			LOG.info(actionReturnMessage);

		} catch (Exception e) {
			actionReturnMessage = e.toString();
			LOG.error(actionReturnMessage);
		}

		initServletContextObject();
		UserviewVO userview = (UserviewVO) session.getAttribute("userview");
		Integer year = (Integer) session.getAttribute("thisyear");
		List<UseractsVO> useractsVO = userviewBean.doGetAllUserActsByUserId(userId, year);
		session.setAttribute("acts", useractsVO);
		return "showActs";
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<String> getGroupName() {
		return groupName;
	}

	public void setGroupName(List<String> groupName) {
		this.groupName = groupName;
	}

	public Date getOut_date() {
		return out_date;
	}

	public void setOut_date(Date outDate) {
		out_date = outDate;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserDept() {
		return this.userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public Date getIn_date() {
		return this.in_date;
	}

	public void setIn_date(Date inDate) {
		this.in_date = inDate;
	}

	public Float getSpending() {
		return spending;
	}

	public void setSpending(Float spending) {
		this.spending = spending;
	}

	public Float getQuota() {
		return this.quota;
	}

	public void setQuota(Float quota) {
		this.quota = quota;
	}

	public Float getRemaining() {
		return this.remaining;
	}

	public void setRemaining(Float remaining) {
		this.remaining = remaining;
	}

	public Integer getParticipatorNO() {
		return this.participatorNO;
	}

	public void setParticipatorNO(Integer participatorNO) {
		this.participatorNO = participatorNO;
	}

	public Float getConsumption() {
		return this.consumption;
	}

	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
