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
import com.tu.model.userlogin.UserloginManageInterface;
import com.tu.model.userlogin.UserloginVO;
import com.tu.model.userview.UserMsgVO;
import com.tu.model.userview.UseractsVO;
import com.tu.model.userview.UserviewInterface;

public class UserviewAction extends AbstractAction {

	private static final Log LOGGER = LogFactory.getLog(UserviewAction.class);
	private static final long serialVersionUID = -6352178618288011965L;
	
	private transient UserloginManageInterface userloginManageBean = null;
	private transient UserviewInterface userviewBean = null;
	private transient UserActInterface userActBean = null;
	private transient UserMsgInterface userMsgBean = null;
	private transient ActivitiesInterface actsBean = null;

	// 接收调用页的相应控件值，正常返回后传给success对应页面的参数
	private Integer nbParticipants;
	private Float consumption;
	private String remark;

	public UserviewAction() {
		// do nothing
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

	public UserActInterface getUserActBean() {
		return this.userActBean;
	}

	public void setUserActBean(UserActInterface userActBean) {
		this.userActBean = userActBean;
	}

	public UserloginManageInterface getUserloginManageBean() {
		return this.userloginManageBean;
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
		List<UseractsVO> useracts = (List<UseractsVO>) session
				.getAttribute("acts");
		int oneactId = Integer.parseInt(request.getParameter("actId"));
		for (UseractsVO userActVO : useracts) {
			int actId = userActVO.getActId();
			LOGGER.info("the actId is" + actId);
			LOGGER.info("the actName is" + userActVO.getActName());
			if (oneactId == actId) {
				request.setAttribute("act", userActVO);
			}
		}
		return "showDetails";
	}

	// 显示所有messages
	public String doshowMessages() {
		initServletContextObject();
		Integer userId = (Integer) session.getAttribute("userId");
		List<UserMsgVO> messages = userviewBean.dogetMessages(userId);
		List<UserMsgVO> overmessages = new ArrayList<UserMsgVO>();
		List<UserMsgVO> inmessages = new ArrayList<UserMsgVO>();

		for (int i = 0; i < messages.size(); i++) {
			ActivitiesVO oneAct = actsBean.doGetOneActById(messages.get(i).getActId());

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
		List<UserMsgVO> messages = (List<UserMsgVO>) session.getAttribute("inmessages");
		Integer msgId = Integer.parseInt(request.getParameter("msgId"));
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).getMsgId().equals(msgId)) {
				session.setAttribute("msgDetails", messages.get(i));
			}
		}

		// 将UserMsg中的状态设置为已读
		Integer userId = (Integer) session.getAttribute("userId");
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

	// 跳转到参与某个活动的界面
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

	// 请求参与某个活动具体操作
	public String doActRequest() {
		initServletContextObject();
		UserActVO oneUserActVO = new UserActVO();
		Integer actId = (Integer) session.getAttribute("inActId");
		LOGGER.info("the inActid is:" + actId);
		Integer userId = (Integer) session.getAttribute("userId");
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
					+ "get success");
		}
		session.setAttribute("acts", useractsVO);
		session.setAttribute("thisyear", year);
		return "showActs";
	}

	// 修改密码
	public String doChangePwd() {
		String updateMessage = null;
		initServletContextObject();
		String oldpassword = (String) request.getParameter("oldpassword");
		String newpassword = (String) request.getParameter("newpassword");
		Integer userId = (Integer) session.getAttribute("userId");
		LOGGER.info("the userId is:" + userId);
		UserloginVO userInfo = userloginManageBean.dogetOneUserInfoByUserId(userId);
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

	// 删除一个想参与的活动（特别的，若该活动报名时间结束则不能再删除）
	public String doDeleteOneAct() {
		String actionReturnMessage = null;
		initServletContextObject();
		Integer userId = (Integer) session.getAttribute("userId");
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
