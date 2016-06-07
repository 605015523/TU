package leaderview.action;

import group.model.GroupVO;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import leaderview.model.GroupActVO;
import leaderview.model.LeaderviewInterface;
import leaderview.model.memberInVO;
import messages.model.MessagesInterface;
import messages.model.MessagesVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import user_act.model.User_actInterface;
import user_act.model.User_actVO;
import user_group.model.User_groupInterface;
import user_msg.model.User_msgInterface;

import activities.model.ActivitiesInterface;
import activities.model.ActivitiesVO;

import com.opensymphony.xwork2.ActionSupport;

public class LeaderviewAction extends ActionSupport {
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private HttpSession session = null;

	private LeaderviewInterface leaderviewBean = null;
	private User_groupInterface user_groupBean = null;
	private ActivitiesInterface actsBean = null;
	private MessagesInterface msgBean = null;
	private User_msgInterface user_msgBean = null;
	private User_actInterface user_actBean = null;

	// ���յ���ҳ����Ӧ�ؼ�ֵ���������غ󴫸�success��Ӧҳ��Ĳ���
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
	private Integer participatorNO;
	private Float sum;

	public LeaderviewAction() {

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

	public User_groupInterface getUser_groupBean() {
		return this.user_groupBean;
	}

	public void setUser_groupBean(User_groupInterface userGroupBean) {
		this.user_groupBean = userGroupBean;
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

	public void setUser_msgBean(User_msgInterface user_MsgBean) {
		this.user_msgBean = user_MsgBean;
	}

	public User_actInterface getUser_actBean() {
		return user_actBean;
	}

	public void setUser_actBean(User_actInterface userActBean) {
		user_actBean = userActBean;
	}

	public void initServletContextObject() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
	}

	// ���һ������������������ݿ⣬�����͸�����С���������
	public String doAddAct() throws ParseException {
		initServletContextObject();
		String addMessage = null;
		String sendMessage = null;
		String addActMsg = null;

		// ��ҳ���������ֵ�������oneActVO��
		ActivitiesVO oneActVO = new ActivitiesVO();
		oneActVO.setActName(getActName());
		oneActVO.setActMoney(getActMoney());
		oneActVO.setDescription(getDescription());
		oneActVO.setActDate(getActDate());
		GroupVO group = new GroupVO();
		group = (GroupVO) session.getAttribute("group");
		oneActVO.setGroupId(group.getGroupId());

		// ���ڱ�����ʼ���ںͽ������ڴ�����һ��string��daterange���У���������Ҫ���и�ʽת��
		SimpleDateFormat act = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = act.parse(daterange.substring(0, 10));
		Date endDate = act.parse(daterange.substring(13, 23));
		oneActVO.setEnrollStartDate(startDate);
		oneActVO.setEnrollEndDate(endDate);
		oneActVO.setState("draft");

		// ���һ��������ݿⲢ���ػ��Id
		try {
			actId = actsBean.doAddOneAct(oneActVO);
			addMessage = "add act success";
		} catch (Exception e) {
			addMessage = "there are something wrong with control layer: "
					+ e.toString();
		}

		initServletContextObject();
		List<GroupActVO> groupactsVO = new ArrayList<GroupActVO>();
		groupactsVO = leaderviewBean.doGetAllUserActsByGroupId(group
				.getGroupId());
		System.out.println("the doGetAllGroupAct get success");
		session.setAttribute("groupacts", groupactsVO);

		return "ShowAllGroupAct";
	}

	// ��ʾ��С�����л������е�����ֵ���뵽view_act.jsp����
	public String doGetAllGroupAct() {
		initServletContextObject();
		List<GroupActVO> groupactsVO = new ArrayList<GroupActVO>();
		GroupVO group = (GroupVO) session.getAttribute("group");
		Integer groupId = group.getGroupId();
		groupactsVO = leaderviewBean.doGetAllUserActsByGroupId(groupId);
		System.out.println("the doGetAllGroupAct get success");
		session.setAttribute("groupacts", groupactsVO);

		return "ShowAllGroupAct";
	}

	// ����ʱ��ת��ѡ�����ϸ��ҳ�棬���û�е�����ֵ����view_act_details.jspҳ��
	public String doshowActDetails() {
		initServletContextObject();
		List<GroupActVO> groupactsVO = new ArrayList<GroupActVO>();
		groupactsVO = (List<GroupActVO>) session.getAttribute("groupacts");
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

	// �����޸Ľ���
	public String doEditAct() {
		initServletContextObject();
		List<GroupActVO> groupactsVO = new ArrayList<GroupActVO>();
		groupactsVO = (List<GroupActVO>) session.getAttribute("groupacts");
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

	// �����ʱ���༭����
	public String doEditActDetails() {
		initServletContextObject();
		List<GroupActVO> groupactsVO = new ArrayList<GroupActVO>();
		groupactsVO = (List<GroupActVO>) session.getAttribute("groupacts");
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

	// ����һ�����ϸ��
	public String doUpdateActDetails() {
		initServletContextObject();
		List<GroupActVO> groupactsVO = new ArrayList<GroupActVO>();
		Integer updateActId = (Integer) session.getAttribute("updateActId");
		groupactsVO = (List<GroupActVO>) session.getAttribute("groupacts");
		for (int i = 0; i < groupactsVO.size(); i++) {
			int actId = groupactsVO.get(i).getActId();
			if (updateActId == actId) {
				GroupActVO onegroupactVO = groupactsVO.get(i);
				onegroupactVO.setActMoney(getActMoney());
				onegroupactVO.setSum(getSum());
				onegroupactVO.setParticipatorNO(getParticipatorNO());

				ActivitiesVO oneActVO = new ActivitiesVO();
				oneActVO = actsBean.doGetOneActById(updateActId);
				oneActVO.setActMoney(getActMoney());
				try {
					actsBean.doUpdateOneAct(oneActVO);
				} catch (Exception e) {
				}
				ArrayList<memberInVO> MemberInVO = new ArrayList<memberInVO>();

				for (int j = 1; j < (onegroupactVO.getMemberInVO().size() + 1); j++) {
					memberInVO oneMemberInVO = onegroupactVO.getMemberInVO()
							.get(j - 1);
					User_actVO oneuser_act = new User_actVO();
					oneuser_act = user_actBean.doGetOneActById(
							oneMemberInVO.getUserId(), updateActId);
					Float consumption = Float.parseFloat(request
							.getParameter("perconsumption_" + j));
					oneuser_act.setConsumption(consumption);
					oneMemberInVO.setConsumption(consumption);
					Integer participatorNO = Integer.parseInt(request
							.getParameter("perparticipatorNO_" + j));
					oneuser_act.setParticipatorNO(participatorNO);
					oneMemberInVO.setParticipatorNO(participatorNO);
					MemberInVO.add(oneMemberInVO);
					// ���û�����ݿ��е����ݸ��£�����activitiesImple�е�doUpdateOneAct
					try {
						user_actBean.doUpdateOneUser_act(oneuser_act);
					} catch (Exception e) {
					}

				}
				onegroupactVO.setMemberInVO(MemberInVO);
				request.setAttribute("act", onegroupactVO);
			}
		}

		return "ShowActDetails";
	}

	// ���»�����ݿ⣬�����͸�����Ϣ�����г�Ա
	public String doUpdateAct() throws Exception {
		String updateMessage = null;
		String sendMessage = null;
		String addMessage = null;

		// �����и��µĻ��Ϣ���뵽oneActVO��
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
		oneActVO.setState("draft");

		// ���û�����ݿ��е����ݸ��£�����activitiesImple�е�doUpdateOneAct
		try {
			updateMessage = actsBean.doUpdateOneAct(oneActVO);
			System.out.println("updateMessage" + updateMessage);

		} catch (Exception e) {
			updateMessage = "there are something wrong with control layer: "
					+ e.toString();
		}

		initServletContextObject();
		List<GroupActVO> groupactsVO = new ArrayList<GroupActVO>();
		// GroupVO group = (GroupVO) session.getAttribute("group");
		Integer groupId = group.getGroupId();
		groupactsVO = leaderviewBean.doGetAllUserActsByGroupId(groupId);
		System.out.println("the doGetAllGroupAct get success");
		session.setAttribute("groupacts", groupactsVO);
		return "ShowAllGroupAct";
	}

	// publish�����������˽��յ����������һ��messages
	public String doPublishAct() {
		initServletContextObject();
		String sendMessage = null;
		String addMessage = null;
		String updateMessage = null;

		ActivitiesVO oneActVO = new ActivitiesVO();
		int actId = Integer.parseInt(request.getParameter("actId"));
		oneActVO = actsBean.doGetOneActById(actId);
		oneActVO.setState("publish");

		// ���û�����ݿ��е����ݸ��£�����activitiesImple�е�doUpdateOneAct
		try {
			updateMessage = actsBean.doUpdateOneAct(oneActVO);

		} catch (Exception e) {
			updateMessage = "there are something wrong with control layer: "
					+ e.toString();
		}

		// ��activity���������Ե�ֵcopy��message��
		MessagesVO oneMsgVO = new MessagesVO();
		try {
			BeanUtils.copyProperties(oneMsgVO, oneActVO);
		} catch (IllegalAccessException e) {
			System.out.println(" there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			System.out.println("there is a InvocationTargetException");
		}

		// �����message�浽���ݿ⣬���ҷ��͸������û�
		initServletContextObject();
		ArrayList<Integer> allMemberId = new ArrayList<Integer>();
		allMemberId = (ArrayList<Integer>) session.getAttribute("allMemberId");
		try {
			msgId = msgBean.doAddOneMsg(oneMsgVO);
			sendMessage = user_msgBean.doSendMsg(msgId, allMemberId);

		} catch (Exception e) {
			addMessage = "there are something wrong with control layer: "
					+ e.toString();
		}
		// ����session�е�year�����Ѵ������year����ִ�в������������year��ӵ�years��
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
		List<GroupActVO> groupactsVO = new ArrayList<GroupActVO>();
		GroupVO group = (GroupVO) session.getAttribute("group");
		Integer groupId = group.getGroupId();
		groupactsVO = leaderviewBean.doGetAllUserActsByGroupId(groupId);
		System.out.println("the doGetAllGroupAct get success");
		session.setAttribute("groupacts", groupactsVO);
		return "ShowAllGroupAct";
	}

	// �������ɵĻ�ύ������С��ȥvalidate
	public String doToValidateAct() {
		initServletContextObject();
		ActivitiesVO oneActVO = new ActivitiesVO();
		String validateMessage;
		Integer actId = (Integer) session.getAttribute("validateActId");
		oneActVO = actsBean.doGetOneActById(actId);
		oneActVO.setState("tobevalidate");

		try {
			validateMessage = actsBean.doUpdateOneAct(oneActVO);

		} catch (Exception e) {
			validateMessage = "there are something wrong with control layer: "
					+ e.toString();
		}
		initServletContextObject();
		List<GroupActVO> groupactsVO = new ArrayList<GroupActVO>();
		GroupVO group = (GroupVO) session.getAttribute("group");
		Integer groupId = group.getGroupId();
		groupactsVO = leaderviewBean.doGetAllUserActsByGroupId(groupId);
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

	public Integer getParticipatorNO() {
		return participatorNO;
	}

	public void setParticipatorNO(Integer participatorNO) {
		this.participatorNO = participatorNO;
	}

	public Float getSum() {
		return sum;
	}

	public void setSum(Float sum) {
		this.sum = sum;
	}

}
