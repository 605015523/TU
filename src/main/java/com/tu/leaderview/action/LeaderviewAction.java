package com.tu.leaderview.action;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tu.activities.model.ActivitiesConstant;
import com.tu.activities.model.ActivitiesInterface;
import com.tu.activities.model.ActivitiesVO;
import com.tu.group.model.GroupVO;
import com.tu.leaderview.model.GroupActVO;
import com.tu.leaderview.model.LeaderviewInterface;
import com.tu.leaderview.model.MemberInVO;
import com.tu.messages.model.MessagesInterface;
import com.tu.messages.model.MessagesVO;
import com.tu.user.act.model.UserActInterface;
import com.tu.user.act.model.UserActVO;
import com.tu.user.group.model.UserGroupInterface;
import com.tu.user.msg.model.UserMsgInterface;

public class LeaderviewAction extends ActionSupport {
	private static final long serialVersionUID = -1552527472504308094L;
	
	private static final Log LOGGER = LogFactory.getLog(LeaderviewAction.class);
	
	private transient HttpServletRequest request = null;
	private transient HttpServletResponse response = null;
	private transient HttpSession session = null;

	private transient LeaderviewInterface leaderviewBean = null;
	private transient UserGroupInterface userGroupBean = null;
	private transient ActivitiesInterface actsBean = null;
	private transient MessagesInterface msgBean = null;
	private transient UserMsgInterface userMsgBean = null;
	private transient UserActInterface userActBean = null;

	// 接收调用页的相应控件值，正常返回后传给success对应页面的参数
	private Integer msgId;
	private Integer userId;
	private String userName;
	private String userDept;
	private Integer actId;
	private Integer groupId;
	private String actName;
	private Float actMoney;
	private String description;
	private Date actDate;
	private String daterange;
	private Integer nbParticipants;
	private Float sum;

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

	public UserGroupInterface getUserGroupBean() {
		return this.userGroupBean;
	}

	public void setUserGroupBean(UserGroupInterface userGroupBean) {
		this.userGroupBean = userGroupBean;
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

	public void initServletContextObject() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
	}

	// 添加一个活动，将这个活动存入数据库，并发送给审批小组进行审批
	public String doAddAct() throws ParseException {
		initServletContextObject();
		String addMessage = null;
		String sendMessage = null;
		String addActMsg = null;

		// 将页面上输入的值存入对象oneActVO中
		ActivitiesVO oneActVO = new ActivitiesVO();
		oneActVO.setActName(getActName());
		oneActVO.setActMoney(getActMoney());
		oneActVO.setDescription(getDescription());
		oneActVO.setActDate(getActDate());
		GroupVO group = (GroupVO) session.getAttribute("group");
		oneActVO.setGroupId(group.getGroupId());

		// 由于报名开始日期和结束日期存在了一个string（daterange）中，所以这里要进行格式转换
		SimpleDateFormat act = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = act.parse(daterange.substring(0, 10));
		Date endDate = act.parse(daterange.substring(13, 23));
		oneActVO.setEnrollStartDate(startDate);
		oneActVO.setEnrollEndDate(endDate);
		oneActVO.setState(ActivitiesConstant.STATE_DRAFT);

		// 添加一个活动到数据库并返回活动的Id
		actId = actsBean.doAddOneAct(oneActVO);
		LOGGER.info("add act success");

		initServletContextObject();
		List<GroupActVO> groupactsVO = leaderviewBean.doGetAllUserActsByGroupId(group
				.getGroupId());
		LOGGER.info("the doAddAct get success");
		session.setAttribute("groupacts", groupactsVO);

		return "ShowAllGroupAct";
	}

	// 显示该小组所有活动，将活动中的所有值传入到view_act.jsp界面
	public String doGetAllGroupAct() {
		initServletContextObject();
		GroupVO group = (GroupVO) session.getAttribute("group");
		Integer groupId = group.getGroupId();
		List<GroupActVO> groupactsVO = leaderviewBean.doGetAllUserActsByGroupId(group.getGroupId());
		LOGGER.info("the doGetAllGroupAct get success");
		session.setAttribute("groupacts", groupactsVO);

		return "ShowAllGroupAct";
	}

	// 调用时跳转到选定活动的细节页面，将该活动中的所有值传入view_act_details.jsp页面
	public String doshowActDetails() {
		initServletContextObject();
		List<GroupActVO> groupactsVO = (List<GroupActVO>) session.getAttribute("groupacts");
		int oneactId = Integer.parseInt(request.getParameter("actId"));
		for (int i = 0; i < groupactsVO.size(); i++) {
			int actId = groupactsVO.get(i).getActId();

			if (oneactId == actId) {
				request.setAttribute("act", groupactsVO.get(i));
				request.setAttribute("actId", actId);
			}
		}
		return "ShowActDetails";

	}

	// 进入活动修改界面
	public String doEditAct() {
		initServletContextObject();
		List<GroupActVO> groupactsVO = (List<GroupActVO>) session.getAttribute("groupacts");
		int oneactId = Integer.parseInt(request.getParameter("actId"));
		for (int i = 0; i < groupactsVO.size(); i++) {
			int actId = groupactsVO.get(i).getActId();
			if (oneactId == actId) {
				request.setAttribute("act", groupactsVO.get(i));
				request.setAttribute("actId", actId);
			}
		}
		return "EditAct";

	}

	// 活动结束时，编辑这个活动
	public String doEditActDetails() {
		initServletContextObject();
		List<GroupActVO> groupactsVO = (List<GroupActVO>) session.getAttribute("groupacts");
		int oneactId = Integer.parseInt(request.getParameter("actId"));
		for (int i = 0; i < groupactsVO.size(); i++) {
			int actId = groupactsVO.get(i).getActId();
			if (oneactId == actId) {
				request.setAttribute("act", groupactsVO.get(i));
				request.setAttribute("actId", actId);
			}
		}
		return "EditActDetails";

	}

	// 更新一个活动的细节
	public String doUpdateActDetails() {
		initServletContextObject();
		Integer updateActId = (Integer) session.getAttribute("updateActId");
		List<GroupActVO> groupactsVO = (List<GroupActVO>) session.getAttribute("groupacts");
		for (int i = 0; i < groupactsVO.size(); i++) {
			int actId = groupactsVO.get(i).getActId();
			if (updateActId == actId) {
				GroupActVO onegroupactVO = groupactsVO.get(i);
				onegroupactVO.setActMoney(getActMoney());
				onegroupactVO.setSum(getSum());
				onegroupactVO.setNbParticipants(getNbParticipants());

				ActivitiesVO oneActVO = actsBean.doGetOneActById(updateActId);
				oneActVO.setActMoney(getActMoney());
				try {
					actsBean.doUpdateOneAct(oneActVO);
				} catch (Exception e) {
				}
				List<MemberInVO> memberInVO = new ArrayList<MemberInVO>();

				for (int j = 1; j < (onegroupactVO.getMemberInVO().size() + 1); j++) {
					MemberInVO oneMemberInVO = onegroupactVO.getMemberInVO()
							.get(j - 1);
					UserActVO oneuserAct = userActBean.doGetOneActById(
							oneMemberInVO.getUserId(), updateActId);
					Float consumption = Float.parseFloat(request
							.getParameter("perconsumption_" + j));
					oneuserAct.setConsumption(consumption);
					oneMemberInVO.setConsumption(consumption);
					Integer nbParticipants = Integer.parseInt(request
							.getParameter("perNbParticipants_" + j));
					oneuserAct.setNbParticipants(nbParticipants);
					oneMemberInVO.setNbParticipants(nbParticipants);
					memberInVO.add(oneMemberInVO);
					// 将该活动在数据库中的数据更新，调用activitiesImple中的doUpdateOneAct
					try {
						userActBean.doUpdateOneUserAct(oneuserAct);
					} catch (Exception e) {
					}

				}
				onegroupactVO.setMemberInVO(memberInVO);
				request.setAttribute("act", onegroupactVO);
			}
		}

		return "ShowActDetails";
	}

	// 更新活动到数据库，并发送更新消息给所有成员
	public String doUpdateAct() throws ParseException {
		String updateMessage = null;
		String sendMessage = null;
		String addMessage = null;

		// 将所有更新的活动消息存入到oneActVO中
		initServletContextObject();
		ActivitiesVO oneActVO = new ActivitiesVO();
		Integer updateActId = (Integer) session.getAttribute("updateActId");
		oneActVO.setActId(updateActId);
		GroupVO group = (GroupVO) session.getAttribute("group");
		Integer onegroupId = group.getGroupId();
		oneActVO.setGroupId(onegroupId);
		oneActVO.setActName(actName);
		oneActVO.setActMoney(actMoney);
		oneActVO.setActDate(actDate);
		oneActVO.setDescription(description);
		SimpleDateFormat act = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = act.parse(daterange.substring(0, 10));
		Date endDate = act.parse(daterange.substring(13, 23));
		oneActVO.setEnrollStartDate(startDate);
		oneActVO.setEnrollEndDate(endDate);
		oneActVO.setState(ActivitiesConstant.STATE_DRAFT);

		// 将该活动在数据库中的数据更新，调用activitiesImple中的doUpdateOneAct
		updateMessage = actsBean.doUpdateOneAct(oneActVO);
		LOGGER.info(updateMessage);

		// GroupVO group = (GroupVO) session.getAttribute("group");
		Integer groupId = group.getGroupId();
		List<GroupActVO> groupactsVO = leaderviewBean.doGetAllUserActsByGroupId(groupId);
		LOGGER.info("the doUpdateAct get success");
		session.setAttribute("groupacts", groupactsVO);
		return "ShowAllGroupAct";
	}

	// publish这个活动，所有人将收到这个活动发起的一个messages
	public String doPublishAct() {
		initServletContextObject();
		String sendMessage = null;
		String addMessage = null;
		String updateMessage = null;

		int actId = Integer.parseInt(request.getParameter("actId"));
		ActivitiesVO oneActVO = actsBean.doGetOneActById(actId);
		oneActVO.setState(ActivitiesConstant.STATE_PUBLISH);

		// 将该活动在数据库中的数据更新，调用activitiesImple中的doUpdateOneAct
		try {
			updateMessage = actsBean.doUpdateOneAct(oneActVO);

		} catch (Exception e) {
			updateMessage = "there are something wrong with control layer: "
					+ e.toString();
		}

		// 将activity中所有属性的值copy到message中
		MessagesVO oneMsgVO = new MessagesVO();
		try {
			BeanUtils.copyProperties(oneMsgVO, oneActVO);
		} catch (IllegalAccessException e) {
			LOGGER.error("there is a IllegalAccessException: " + e.toString());
		} catch (InvocationTargetException e) {
			LOGGER.error("there is a InvocationTargetException: " + e.toString());
		}

		// 将这个message存到数据库，并且发送给所有用户
		initServletContextObject();
		List<Integer> allMemberId = (List<Integer>) session.getAttribute("allMemberId");
		try {
			msgId = msgBean.doAddOneMsg(oneMsgVO);
			sendMessage = userMsgBean.doSendMsg(msgId, allMemberId);

		} catch (Exception e) {
			addMessage = "there are something wrong with control layer: "
					+ e.toString();
		}
		// 更新session中的year，若已存在这个year，则不执行操作，否则将这个year添加到years中
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

		initServletContextObject();
		GroupVO group = (GroupVO) session.getAttribute("group");
		Integer groupId = group.getGroupId();
		List<GroupActVO> groupactsVO = leaderviewBean.doGetAllUserActsByGroupId(groupId);
		LOGGER.info("the doPublishAct get success");
		session.setAttribute("groupacts", groupactsVO);
		return "ShowAllGroupAct";
	}

	// 将这个完成的活动提交到申请小组去validate
	public String doToValidateAct() {
		initServletContextObject();
		String validateMessage;
		Integer actId = (Integer) session.getAttribute("validateActId");
		ActivitiesVO oneActVO = actsBean.doGetOneActById(actId);
		oneActVO.setState("tobevalidate");

		try {
			validateMessage = actsBean.doUpdateOneAct(oneActVO);

		} catch (Exception e) {
			validateMessage = "there are something wrong with control layer: "
					+ e.toString();
		}
		initServletContextObject();
		GroupVO group = (GroupVO) session.getAttribute("group");
		Integer groupId = group.getGroupId();
		List<GroupActVO> groupactsVO = leaderviewBean.doGetAllUserActsByGroupId(groupId);
		session.setAttribute("groupacts", groupactsVO);
		return "ShowAllGroupAct";
	}

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

	public String getUserDept() {
		return this.userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public Integer getActId() {
		return this.actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
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

}
