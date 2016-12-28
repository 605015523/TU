package com.tu.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.model.activities.ActivitiesConstant;
import com.tu.model.activities.ActivitiesInterface;
import com.tu.model.activities.ActivityVO;
import com.tu.model.group.GroupInterface;
import com.tu.model.group.GroupVO;
import com.tu.model.leaderview.GroupActVO;
import com.tu.model.leaderview.LeaderviewInterface;
import com.tu.model.user.act.UserActInterface;
import com.tu.model.user.act.UserActVO;
import com.tu.util.ConfConstants;

public class LeaderviewAction extends AbstractAction {
	private static final long serialVersionUID = -1552527472504308094L;
	
	private static final Log LOGGER = LogFactory.getLog(LeaderviewAction.class);
	
	private transient LeaderviewInterface leaderviewBean = null;
	private transient ActivitiesInterface actsBean = null;
	private transient GroupInterface groupBean = null;
	private transient UserActInterface userActBean = null;

	// Receive the controls value from the caller page and return the parameters to the "success" web page 
	private Integer actId;
	private String actName;
	private BigDecimal actMoney;
	private String description;
	private Date actDate;
	private String daterange;
	private Integer nbParticipants;
	private BigDecimal sum;
	
	// To display one group activity
	private GroupActVO groupAct;
	private GroupVO group;
	
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
		Integer groupId = (Integer) session.getAttribute("groupId");
		oneActVO.setGroupId(groupId);

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
		Integer groupId = (Integer) session.getAttribute("groupId");
		groupActs = leaderviewBean.doGetAllUserActsByGroupId(groupId);
		group = groupBean.doGetOneGroupById(groupId);
		LOGGER.info("the doGetAllGroupAct get success");

		return "ShowAllGroupAct";
	}

	// Get and put all the detail of one activity in view_act_details.jsp
	public String doshowActDetails() {
		initServletContextObject();
		groupAct = leaderviewBean.doGetUserActById(actId);
		
		return "ShowActDetails";

	}

	// Go into activity modification web page
	public String doEditAct() {
		initServletContextObject();
		groupAct = leaderviewBean.doGetUserActById(actId);
		String state = groupAct.getActivity().getState();

		List<String> finishedStates = Arrays.asList("pending", "validate", "publish", "tobevalidate", "tobeapproved");
		
		if (!finishedStates.contains(state)) {
			return "EditAct";
		}
		
		if ("pending".equals(state)) {
			return "EditActDetails";
		}
		
		return "Error";
	}

	// Update the activity detail in this page
	public String doUpdateActDetails() {
		initServletContextObject();
		groupAct = leaderviewBean.doGetUserActById(actId);
		
		groupAct.getActivity().setActMoney(getActMoney());
		groupAct.setSum(sum);
		groupAct.setNbParticipants(nbParticipants);

		ActivityVO oneActVO = actsBean.doGetOneActById(actId);
		oneActVO.setActMoney(getActMoney());
		actsBean.doUpdateOneAct(oneActVO);
		
		List<UserActVO> memberInVO = new ArrayList<UserActVO>();

		for (int j = 1; j < (groupAct.getMemberInVO().size() + 1); j++) {
			UserActVO oneMemberInVO = groupAct.getMemberInVO().get(j - 1);
			
			BigDecimal consumption = new BigDecimal(request.getParameter("perconsumption_" + j));
			oneMemberInVO.setConsumption(consumption);
			
			Integer nbParticipantsGrpAct = Integer.parseInt(request.getParameter("perNbParticipants_" + j));
			oneMemberInVO.setNbParticipants(nbParticipantsGrpAct);
			
			memberInVO.add(oneMemberInVO);
			// Write the updated detail into database by calling method doUpdateOneAct of class activitiesImple
			userActBean.doUpdateOneUserAct(oneMemberInVO);
		}
		groupAct.setMemberInVO(memberInVO);

		return "ShowActDetails";
	}
	
	
	public String addActForm() {
		initServletContextObject();
		Integer groupId = (Integer) session.getAttribute("groupId");
		group = groupBean.doGetOneGroupById(groupId);
		
		return "addActForm";
	}

	// Update the activity message and send it to everyone
	public String doUpdateAct() throws ParseException {
		String updateMessage = null;

		// oneActVO stores all the updated activity messages
		initServletContextObject();
		ActivityVO oneActVO = createActivityFromForm(ActivitiesConstant.STATE_DRAFT);

		// Write updated activity into database by call method doUpdateOneAct of class activitiesImple
		updateMessage = actsBean.doUpdateOneAct(oneActVO);
		LOGGER.info(updateMessage);
		LOGGER.info("the doUpdateAct get success");
		
		return "redirectToDoGetAllGroupAct";
	}
	
	private ActivityVO createActivityFromForm(String state) throws ParseException {
		ActivityVO oneActVO = new ActivityVO();
		oneActVO.setActId(actId);
		Integer groupId = (Integer) session.getAttribute("groupId");
		oneActVO.setGroupId(groupId);
		oneActVO.setActName(actName);
		oneActVO.setActMoney(actMoney);
		oneActVO.setActDate(actDate);
		oneActVO.setDescription(description);
		SimpleDateFormat act = new SimpleDateFormat(ConfConstants.DATE_FORMAT);
		Date startDate = act.parse(daterange.substring(0, 10));
		Date endDate = act.parse(daterange.substring(13, 23));
		oneActVO.setEnrollStartDate(startDate);
		oneActVO.setEnrollEndDate(endDate);
		oneActVO.setState(state);
		
		return oneActVO;
	}
	
	public String doUpdateAndSubmitAct() throws ParseException {
		String updateMessage = null;

		// oneActVO stores all the updated activity messages
		initServletContextObject();
		ActivityVO oneActVO = createActivityFromForm(ActivitiesConstant.STATE_TOBEAPPROVED);

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

		// Update variable year in session. If the year exists, ignore, or append year into attribute years
		List<Integer> years = (List<Integer>) session.getAttribute("years");
		Calendar cal = Calendar.getInstance();
		cal.setTime(oneActVO.getActDate());
		Integer thisyear = cal.get(Calendar.YEAR);
		if (!years.isEmpty() && !thisyear.equals(years.get(years.size() - 1))) {
			years.add(thisyear);
		}

		LOGGER.info("the doPublishAct get success");
		
		return "redirectToDoGetAllGroupAct";
	}

	// Submit the activity into approval group for validating
	public String doToValidateAct() {
		ActivityVO oneActVO = actsBean.doGetOneActById(actId);
		oneActVO.setState(ActivitiesConstant.STATE_TOBEVALIDATE);

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

	public BigDecimal getActMoney() {
		return this.actMoney;
	}

	public void setActMoney(BigDecimal actMoney) {
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

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
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
	
	public GroupInterface getGroupBean() {
		return groupBean;
	}

	public void setGroupBean(GroupInterface groupBean) {
		this.groupBean = groupBean;
	}

	public GroupVO getGroup() {
		return group;
	}

}
