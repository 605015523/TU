package com.tu.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.user.msg.UserMsg;
import com.tu.model.activities.ActivitiesConstant;
import com.tu.model.activities.ActivitiesInterface;
import com.tu.model.activities.ActivitiesVO;
import com.tu.model.user.act.UserActInterface;
import com.tu.model.user.act.UserActVO;
import com.tu.model.user.msg.UserMsgConstants;
import com.tu.model.user.msg.UserMsgInterface;
import com.tu.model.userlogin.UserloginVO;
import com.tu.model.userview.UserMsgVO;
import com.tu.model.userview.UseractsVO;
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
		Integer year = Integer.valueOf(request.getParameter("year"));
		List<UseractsVO> useractsVO = userviewBean.doGetAllUserActsByUserId(userId, year);
		session.setAttribute("acts", useractsVO);
		session.setAttribute("thisyear", year);
		return "showActs";
	}

	// Display the specific activity's detail
	public String doshowDetails() {
		initServletContextObject();
		List<UseractsVO> useracts = (List<UseractsVO>) session
				.getAttribute("acts");
		int oneactId = Integer.parseInt(request.getParameter("actId"));
		for (UseractsVO userActVO : useracts) {
			if (oneactId == userActVO.getActId()) {
				LOGGER.info("the actId is" + oneactId);
				LOGGER.info("the actName is" + userActVO.getActName());
				request.setAttribute("act", userActVO);
			}
		}
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
			ActivitiesVO oneAct = actsBean.doGetOneActById(message.getActId());

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
		Integer msgId = Integer.parseInt(request.getParameter("msgId"));
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
		Integer msgId = Integer.parseInt(request.getParameter("msgId"));
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).getMsgId().equals(msgId)) {
				session.setAttribute("message", messages.get(i));
				session.setAttribute("inActId", messages.get(i).getActId());
			}
		}
		return "doInAct";
	}

	// Request to join one activity
	public String doActRequest() {
		initServletContextObject();
		UserActVO oneUserActVO = new UserActVO();
		Integer actId = (Integer) session.getAttribute("inActId");
		LOGGER.info("the inActid is:" + actId);
		Integer userId = getCurrentUser().getUserId();
		oneUserActVO.setActId(actId);
		oneUserActVO.setUserId(userId);
		oneUserActVO.setNbParticipants(nbParticipants);
		oneUserActVO.setConsumption(consumption);
		oneUserActVO.setRemark(remark);
		
		userActBean.doAddOneUserAct(oneUserActVO);

		List<Integer> years = (List<Integer>) session.getAttribute("years");
		Integer year = years.get(years.size() - 1);
		LOGGER.info("the year in action is:" + year);
		LOGGER.info("the userId in action is:" + userId);
		List<UseractsVO> useractsVO = userviewBean.doGetAllUserActsByUserId(userId, year);

		for (int i = 0; i < useractsVO.size(); i++) {
			LOGGER.info("the act" + useractsVO.get(i).getActName()
					+ " get success");
		}
		session.setAttribute("acts", useractsVO);
		session.setAttribute("thisyear", year);
		return "showActs";
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
		Integer actId = Integer.valueOf(request.getParameter("actId"));
		try {
			actionReturnMessage = userActBean.doDeleteOneUserAct(userId,
					actId);
			LOGGER.info(actionReturnMessage);

		} catch (Exception e) {
			actionReturnMessage = e.toString();
			LOGGER.error(actionReturnMessage);
		}

		Integer year = (Integer) session.getAttribute("thisyear");
		List<UseractsVO> useractsVO = userviewBean.doGetAllUserActsByUserId(userId, year);
		session.setAttribute("acts", useractsVO);
		return "showActs";
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

}
