package com.tu.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.model.activities.ActivitiesConstant;
import com.tu.model.activities.ActivitiesInterface;
import com.tu.model.activities.ActivityVO;
import com.tu.model.user.act.UserActInterface;
import com.tu.model.user.act.UserActVO;
import com.tu.model.userlogin.UserloginVO;
import com.tu.model.userview.UserMsgVO;
import com.tu.model.userview.UserActDetailedVO;
import com.tu.model.userview.UserviewInterface;
import com.tu.model.userview.UserviewVO;

public class UserviewAction extends AbstractAction {

	private static final Log LOGGER = LogFactory.getLog(UserviewAction.class);
	private static final long serialVersionUID = -6352178618288011965L;
	
	private transient UserviewInterface userviewBean = null;
	private transient UserActInterface userActBean = null;
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
	private List<UserMsgVO> overmessages;
	private List<UserMsgVO> inmessages;
	private UserMsgVO msgDetails;
	private UserviewVO userview;
	private ActivityVO activity;

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
		initServletContextObject();
		Integer userId = getCurrentUser().getUserId();
		act = userviewBean.doGetUserActsByUserIdAndActId(userId, actId);
		LOGGER.info("the activity is " + act.getActivity().getActName() + " - " + actId);
		
		return "showDetails";
	}

	// Go jump to one activity's page
	public String doInAct() {
		initServletContextObject();
		Integer userId = this.getCurrentUser().getUserId();
		activity = actsBean.doGetOneActById(actId);
		
		userview = userviewBean.doGetOneUserviewInfoByUserId(userId);
		
		return "doInAct";
	}

	// Request to join one activity
	public String doActRequest() {
		initServletContextObject();
		UserActVO userActVO = new UserActVO();
		LOGGER.info("the actid is: " + actId);
		ActivityVO oneAct = actsBean.doGetOneActById(actId);
		userActVO.setActId(actId);
		userActVO.setUser(getCurrentUser());
		userActVO.setNbParticipants(nbParticipants);
		
		// FIXME: know why consumption is actually not given
		userActVO.setConsumption(oneAct.getActMoney().multiply(BigDecimal.valueOf(nbParticipants)));
		//oneUserActVO.setConsumption(consumption != null ? consumption : 0);
		userActVO.setRemark(remark);
		
		userActBean.doAddOneUserAct(userActVO);

		Calendar cal = Calendar.getInstance();
		cal.setTime(oneAct.getActDate());
		year = cal.get(Calendar.YEAR);

		return "redirectToShowActs";
	}

	// Change password
	public String doChangePwd() {
		String updateMessage = null;
		initServletContextObject();
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		UserloginVO userInfo = getCurrentUser();
		LOGGER.info("the userName is:" + userInfo.getUserName());
		String orgpassword = userInfo.getUserPassword();
		userInfo.setUserPassword(newpassword);
		
		if (orgpassword.equals(oldpassword)) {
			updateMessage = userviewBean.doUpdateOneuserInfo(userInfo);
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

	public List<UserMsgVO> getOvermessages() {
		return overmessages;
	}

	public void setOvermessages(List<UserMsgVO> overmessages) {
		this.overmessages = overmessages;
	}

	public List<UserMsgVO> getInmessages() {
		return inmessages;
	}

	public void setInmessages(List<UserMsgVO> inmessages) {
		this.inmessages = inmessages;
	}

	public UserMsgVO getMsgDetails() {
		return msgDetails;
	}

	public void setMsgDetails(UserMsgVO msgDetails) {
		this.msgDetails = msgDetails;
	}

	public UserviewVO getUserview() {
		return userview;
	}

	public void setUserview(UserviewVO userview) {
		this.userview = userview;
	}

	public ActivityVO getActivity() {
		return activity;
	}

}
