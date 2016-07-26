package com.tu.action;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.model.activities.ActivitiesConstant;
import com.tu.model.activities.ActivitiesInterface;
import com.tu.model.activities.ActivityVO;
import com.tu.model.group.GroupVO;
import com.tu.model.leaderview.GroupActVO;
import com.tu.model.leaderview.LeaderviewInterface;
import com.tu.model.leaderview.MemberInVO;
import com.tu.model.messages.MessagesInterface;
import com.tu.model.messages.MessagesVO;
import com.tu.model.user.act.UserActInterface;
import com.tu.model.user.act.UserActVO;
import com.tu.model.user.msg.UserMsgInterface;
import com.tu.model.userlogin.UserloginVO;
import com.tu.util.ConfConstants;

public class LeaderviewAction extends AbstractAction {
	private static final long serialVersionUID = -1552527472504308094L;
	
	private static final Log LOGGER = LogFactory.getLog(LeaderviewAction.class);
	
	private transient LeaderviewInterface leaderviewBean = null;
	private transient ActivitiesInterface actsBean = null;
	private transient MessagesInterface msgBean = null;
	
	private transient UserMsgInterface userMsgBean = null;
	private transient UserActInterface userActBean = null;

	// Receive the controls value from the caller page and return the parameters to the "success" web page 
	private Integer actId;
	private String actName;
	private Float actMoney;
	private String description;
	private Date actDate;
	private String daterange;
	private Integer nbParticipants;
	private Float sum;
	
	// To display one group activity
	private GroupActVO groupAct;
	
	// To display table of group activities
	private List<GroupActVO> groupActs;

	public LeaderviewAction() {
		// do nothing
	}

	public ActivitiesInterface getActsBean() {
		return this.actsBean;
	}

	public void setActsBean(ActivitiesInterface actsBean) {
		this.actsBean = actsBean;
	}

	public LeaderviewInterface getLeaderviewBean() {
		return this.leaderviewBean;
	}

	public void setLeaderviewBean(LeaderviewInterface leaderviewBean) {
		this.leaderviewBean = leaderviewBean;
	}

	public MessagesInterface getMsgBean() {
		return this.msgBean;
	}

	public void setMsgBean(MessagesInterface msgBean) {
		this.msgBean = msgBean;
	}

	public UserMsgInterface getUserMsgBean() {
		return this.userMsgBean;
	}

	public void setUserMsgBean(UserMsgInterface userMsgBean) {
		this.userMsgBean = userMsgBean;
	}

	public UserActInterface getUserActBean() {
		return userActBean;
	}

	public void setUserActBean(UserActInterface userActBean) {
		this.userActBean = userActBean;
	}

	// Add one activity and send it to approval group
	public String doAddAct() throws ParseException {
		initServletContextObject();

		// Put values from web page in object oneActV0
		ActivityVO oneActVO = new ActivityVO();
		oneActVO.setActName(actName);
		oneActVO.setActMoney(actMoney);
		oneActVO.setDescription(description);
		oneActVO.setActDate(actDate);
		GroupVO group = (GroupVO) session.getAttribute("group");
		oneActVO.setGroupId(group.getGroupId());

		// Convert date format since starting date and ending date stored in the same string variable (daterange)
		SimpleDateFormat act = new SimpleDateFormat(ConfConstants.DATE_FORMAT);
		Date startDate = act.parse(daterange.substring(0, 10));
		Date endDate = act.parse(daterange.substring(13, 23));
		oneActVO.setEnrollStartDate(startDate);
		oneActVO.setEnrollEndDate(endDate);
		oneActVO.setState(ActivitiesConstant.STATE_DRAFT);

		// Add one activity and return activity ID
		actId = actsBean.doAddOneAct(oneActVO);
		LOGGER.info("the doAddAct get success");

		return "redirectToDoGetAllGroupAct";
	}

	// Get the specific group's activities and put them in view_act.jsp
	public String doGetAllGroupAct() {
		initServletContextObject();
		GroupVO group = (GroupVO) session.getAttribute("group");
		groupActs = leaderviewBean.doGetAllUserActsByGroupId(group.getGroupId());
		LOGGER.info("the doGetAllGroupAct get success");

		return "ShowAllGroupAct";
	}

	// Get and put all the detail of one activity in view_act_details.jsp
	public String doshowActDetails() {
		groupAct = leaderviewBean.doGetUserActById(actId);
		
		return "ShowActDetails";

	}

	// Go into activity modification web page
	public String doEditAct() {
		groupAct = leaderviewBean.doGetUserActById(actId);
		
		return "EditAct";
	}

	// when the activity is finished, edit it in this page
	public String doEditActDetails() {
		groupAct = leaderviewBean.doGetUserActById(actId);
		
		return "EditActDetails";
	}

	// Update the activity detail in this page
	public String doUpdateActDetails() {
		initServletContextObject();
		groupAct = leaderviewBean.doGetUserActById(actId);
		
		groupAct.setActMoney(getActMoney());
		groupAct.setSum(sum);
		groupAct.setNbParticipants(nbParticipants);

		ActivityVO oneActVO = actsBean.doGetOneActById(actId);
		oneActVO.setActMoney(getActMoney());
		actsBean.doUpdateOneAct(oneActVO);
		
		List<MemberInVO> memberInVO = new ArrayList<MemberInVO>();

		for (int j = 1; j < (groupAct.getMemberInVO().size() + 1); j++) {
			MemberInVO oneMemberInVO = groupAct.getMemberInVO()
					.get(j - 1);
			UserActVO oneuserAct = userActBean.doGetOneActById(
					oneMemberInVO.getUserId(), actId);
			Float consumption = Float.parseFloat(request
					.getParameter("perconsumption_" + j));
			oneuserAct.setConsumption(consumption);
			oneMemberInVO.setConsumption(consumption);
			Integer nbParticipantsGrpAct = Integer.parseInt(request
					.getParameter("perNbParticipants_" + j));
			oneuserAct.setNbParticipants(nbParticipantsGrpAct);
			oneMemberInVO.setNbParticipants(nbParticipantsGrpAct);
			memberInVO.add(oneMemberInVO);
			// Write the updated detail into database by calling method doUpdateOneAct of class activitiesImple
			userActBean.doUpdateOneUserAct(oneuserAct);
		}
		groupAct.setMemberInVO(memberInVO);

		return "ShowActDetails";
	}

	// Update the activity message and send it to everyone
	public String doUpdateAct() throws ParseException {
		String updateMessage = null;

		// oneActVO stores all the updated activity messages
		initServletContextObject();
		ActivityVO oneActVO = new ActivityVO();
		oneActVO.setActId(actId);
		GroupVO group = (GroupVO) session.getAttribute("group");
		Integer onegroupId = group.getGroupId();
		oneActVO.setGroupId(onegroupId);
		oneActVO.setActName(actName);
		oneActVO.setActMoney(actMoney);
		oneActVO.setActDate(actDate);
		oneActVO.setDescription(description);
		SimpleDateFormat act = new SimpleDateFormat(ConfConstants.DATE_FORMAT);
		Date startDate = act.parse(daterange.substring(0, 10));
		Date endDate = act.parse(daterange.substring(13, 23));
		oneActVO.setEnrollStartDate(startDate);
		oneActVO.setEnrollEndDate(endDate);
		oneActVO.setState(ActivitiesConstant.STATE_DRAFT);

		// Write updated activity into database by call method doUpdateOneAct of class activitiesImple
		updateMessage = actsBean.doUpdateOneAct(oneActVO);
		LOGGER.info(updateMessage);
		LOGGER.info("the doUpdateAct get success");
		
		return "redirectToDoGetAllGroupAct";
	}

	// Publish the activity and then everyone would see the message
	public String doPublishAct() {
		initServletContextObject();

		ActivityVO oneActVO = actsBean.doGetOneActById(actId);
		oneActVO.setState(ActivitiesConstant.STATE_PUBLISH);

		// Write updated activity into database by call method doUpdateOneAct of class activitiesImple
		String updateMessage = actsBean.doUpdateOneAct(oneActVO);
		LOGGER.info(updateMessage);

		// Copy all properties from activity to message
		MessagesVO oneMsgVO = new MessagesVO();
		oneMsgVO.setActivity(oneActVO);

		// Write message into database and send to everyone
		List<UserloginVO> allUserLogins = userloginManageBean.doGetAllUserlogin();
				
		// Retrieve all the user IDs for new message sending 
		List<Integer> allMemberId = new ArrayList<Integer>();
		for (int m = 0; m < allUserLogins.size(); m++) {
			Integer userId = allUserLogins.get(m).getUserId();
			allMemberId.add(userId);
		}
		Integer msgId = msgBean.doAddOneMsg(oneMsgVO);
		String sendMessage = userMsgBean.doSendMsg(msgId, allMemberId);
		LOGGER.info(sendMessage);

		// Update variable year in session. If the year exists, ignore, or append year into attribute years
		List<Integer> years = (List<Integer>) session.getAttribute("years");
		Calendar cal = Calendar.getInstance();
		cal.setTime(oneActVO.getActDate());
		Integer thisyear = cal.get(Calendar.YEAR);
		if (years.size() != 0) {
			if (!thisyear.equals(years.get(years.size() - 1))) {
				years.add(thisyear);
			}
		}

		Integer newMsg = (Integer) session.getAttribute("newMsg");
		newMsg += 1;
		session.setAttribute("newMsg", newMsg);

		LOGGER.info("the doPublishAct get success");
		
		return "redirectToDoGetAllGroupAct";
	}

	// Submit the activity into approval group for validating
	public String doToValidateAct() {
		ActivityVO oneActVO = actsBean.doGetOneActById(actId);
		oneActVO.setState("tobevalidate");

		try {
			String validateMessage = actsBean.doUpdateOneAct(oneActVO);
			LOGGER.info(validateMessage);
		} catch (Exception e) {
			LOGGER.error("there are something wrong with control layer: "
					+ e.toString());
		}
		return "redirectToDoGetAllGroupAct";
	}

	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
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

	public Date getActDate() {
		return this.actDate;
	}

	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}

	public String getDaterange() {
		return this.daterange;
	}

	public void setDaterange(String daterange) {
		this.daterange = daterange;
	}

	public Integer getNbParticipants() {
		return nbParticipants;
	}

	public void setNbParticipants(Integer nbParticipants) {
		this.nbParticipants = nbParticipants;
	}

	public Float getSum() {
		return sum;
	}

	public void setSum(Float sum) {
		this.sum = sum;
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

}
