package com.tu.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.user.msg.UserMsg;
import com.tu.model.activities.ActivitiesConstant;
import com.tu.model.activities.ActivitiesInterface;
import com.tu.model.activities.ActivityVO;
import com.tu.model.user.act.UserActInterface;
import com.tu.model.user.act.UserActVO;
import com.tu.model.user.msg.UserMsgConstants;
import com.tu.model.user.msg.UserMsgInterface;
import com.tu.model.userlogin.UserloginVO;
import com.tu.model.userview.UserMsgVO;
import com.tu.model.userview.UserActDetailedVO;
import com.tu.model.userview.UserviewInterface;

public class UserviewAction extends AbstractAction {

	private static final Log LOGGER = LogFactory.getLog(UserviewAction.class);
	private static final long serialVersionUID = -6352178618288011965L;
	
	private transient UserviewInterface userviewBean = null;
	private transient UserActInterface userActBean = null;
	private transient UserMsgInterface userMsgBean = null;
	private transient ActivitiesInterface actsBean = null;

	// Receive the caller page attributes and return parameters into "success" web page
	private Integer nbParticipants;
	private Float consumption;
	private String remark;
	private Integer actId;
	private Integer msgId;
	private Integer year;
	
	// To display
	private List<UserActDetailedVO> useracts;
	private UserActDetailedVO act;

	public UserviewAction() {
		// do nothing
	}

	public UserviewInterface getUserviewBean() {
		return this.userviewBean;
	}

	public void setUserviewBean(UserviewInterface userviewBean) {
		this.userviewBean = userviewBean;
	}

	public UserActInterface getUserActBean() {
		return this.userActBean;
	}

	public void setUserActBean(UserActInterface userActBean) {
		this.userActBean = userActBean;
	}

	public UserMsgInterface getUserMsgBean() {
		return this.userMsgBean;
	}

	public void setUserMsgBean(UserMsgInterface userMsgBean) {
		this.userMsgBean = userMsgBean;
	}

	public ActivitiesInterface getActsBean() {
		return actsBean;
	}

	public void setActsBean(ActivitiesInterface actsBean) {
		this.actsBean = actsBean;
	}

	// Display all participating activities
	public String doshowActs() {
		initServletContextObject();
		Integer userId = getCurrentUser().getUserId();
		useracts = userviewBean.doGetAllUserActsByUserId(userId, year);
		session.setAttribute("thisyear", year);
		return "showActs";
	}

	// Display the specific activity's detail
	public String doshowDetails() {
		Integer userId = getCurrentUser().getUserId();
		act = userviewBean.doGetUserActsByUserIdAndActId(userId, actId);
		LOGGER.info("the actId is " + actId);
		LOGGER.info("the actName is " + act.getActName());
		
		return "showDetails";
	}

	// Display all the messages
	public String doshowMessages() {
		initServletContextObject();
		Integer userId = getCurrentUser().getUserId();
		List<UserMsgVO> messages = userviewBean.dogetMessages(userId);
		List<UserMsgVO> overmessages = new ArrayList<UserMsgVO>();
		List<UserMsgVO> inmessages = new ArrayList<UserMsgVO>();

		for (UserMsgVO message : messages) {
			ActivityVO oneAct = actsBean.doGetOneActById(message.getActId());

			if (oneAct.getState().equals(ActivitiesConstant.STATE_PUBLISH)) {
				inmessages.add(message);
			} else {
				overmessages.add(message);
			}
		}
		session.setAttribute("inmessages", inmessages);
		session.setAttribute("overmessages", overmessages);
		return "showMessages";
	}

	// Display all the messages' details
	public String doshowMsgDetails() {
		initServletContextObject();
		List<UserMsgVO> messages = (List<UserMsgVO>) session.getAttribute("inmessages");
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).getMsgId().equals(msgId)) {
				session.setAttribute("msgDetails", messages.get(i));
			}
		}

		// Set the status of UserMsg as read.
		Integer userId = getCurrentUser().getUserId();
		UserMsg oneUserMsg = userMsgBean.dogetOneByUserIdAndMsgId(userId, msgId);

		if (oneUserMsg.getReadState().equals(UserMsgConstants.STATE_NEW)) {

			oneUserMsg.setReadState(UserMsgConstants.STATE_READ);
			userMsgBean.doUpdateOneUserMsg(oneUserMsg);
			int newMsg = 0;
			List<UserMsg> userMsgVOs = (List<UserMsg>) userMsgBean
					.doGetUserMsg(userId);
			for (int i = 0; i < userMsgVOs.size(); i++) {

				if (userMsgVOs.get(i).getReadState().equals(UserMsgConstants.STATE_NEW)) {
					newMsg += 1;
				}

			}
			session.setAttribute("newMsg", newMsg);

		}

		return "showMsgDetails";
	}

	// Go jump to one activity's page
	public String doInAct() {
		initServletContextObject();
		List<UserMsgVO> messages = (List<UserMsgVO>) session.getAttribute("inmessages");
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).getMsgId().equals(msgId)) {
				session.setAttribute("message", messages.get(i));
			}
		}
		return "doInAct";
	}

	// Request to join one activity
	public String doActRequest() {
		initServletContextObject();
		UserActVO oneUserActVO = new UserActVO();
		LOGGER.info("the actid is: " + actId);
		Integer userId = getCurrentUser().getUserId();
		oneUserActVO.setActId(actId);
		oneUserActVO.setUserId(userId);
		oneUserActVO.setNbParticipants(nbParticipants);
		
		// FIXME: know why consumption is actually not given
		//ActivityVO oneAct = actsBean.doGetOneActById(actId);
		//oneAct.getActMoney()
		oneUserActVO.setConsumption(consumption != null ? consumption : 0);
		
		oneUserActVO.setRemark(remark);
		
		userActBean.doAddOneUserAct(oneUserActVO);

		List<Integer> years = (List<Integer>) session.getAttribute("years");
		year = years.get(years.size() - 1);

		return "redirectToShowActs";
	}

	// Change password
	public String doChangePwd() {
		String updateMessage = null;
		initServletContextObject();
		String oldpassword = (String) request.getParameter("oldpassword");
		String newpassword = (String) request.getParameter("newpassword");
		UserloginVO userInfo = getCurrentUser();
		LOGGER.info("the userName is:" + userInfo.getUserName());
		String orgpassword = userInfo.getUserPassword();
		userInfo.setUserPassword(newpassword);
		
		if (orgpassword.equals(oldpassword)) {
			updateMessage = userviewBean.doupdateOneuserInfo(userInfo);
			request.setAttribute("updateMessage", updateMessage);
			return "pwdUpdateSuccess";
		} else {
			updateMessage = "Incorrect old password, please re-enter!";
			request.setAttribute("updateMessage", updateMessage);
			return "pwdUpdateFail";
		}

	}

	// Cancel one participated activity ( if the registration time is off, cannot be canceled)
	public String doDeleteOneAct() {
		String actionReturnMessage = null;
		initServletContextObject();
		Integer userId = getCurrentUser().getUserId();
		try {
			actionReturnMessage = userActBean.doDeleteOneUserAct(userId, actId);
			LOGGER.info(actionReturnMessage);

		} catch (Exception e) {
			actionReturnMessage = e.toString();
			LOGGER.error(actionReturnMessage);
		}

		year = (Integer) session.getAttribute("thisyear");
		return "redirectToShowActs";
	}

	public Integer getNbParticipants() {
		return this.nbParticipants;
	}

	public void setNbParticipants(Integer nbParticipants) {
		this.nbParticipants = nbParticipants;
	}

	public Float getConsumption() {
		return this.consumption;
	}

	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}
	
	public List<UserActDetailedVO> getUseracts() {
		return useracts;
	}

	public void setUseracts(List<UserActDetailedVO> useracts) {
		this.useracts = useracts;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public UserActDetailedVO getAct() {
		return act;
	}

	public void setAct(UserActDetailedVO act) {
		this.act = act;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}


}
