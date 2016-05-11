package accountingview.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import activities.model.ActivitiesInterface;
import activities.model.ActivitiesVO;

import group.model.GroupInterface;
import group.model.GroupVO;

import user_group.model.User_groupInterface;

import userlogin.model.UserloginManageInterface;
import userlogin.model.UserloginVO;

import accountingview.model.AccountingviewInterface;
import accountingview.model.GroupActVO;
import accountingview.model.UserGroupCostVO;

public class AccountingviewAction extends ActionSupport {
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private HttpSession session = null;

	private AccountingviewInterface accountingviewBean = null;
	private User_groupInterface user_groupBean = null;
	private ActivitiesInterface actsBean = null;
	private GroupInterface groupBean = null;
	UserloginManageInterface UserloginManageBean = null;

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
	private String checkState;
	private String comment;

	public AccountingviewAction() {

	}

	public ActivitiesInterface getActsBean() {
		return this.actsBean;
	}

	public void setActsBean(ActivitiesInterface actsBean) {
		this.actsBean = actsBean;
	}

	public AccountingviewInterface getAccountingviewBean() {
		return this.accountingviewBean;
	}

	public void setAccountingviewBean(AccountingviewInterface AccountingviewBean) {
		this.accountingviewBean = AccountingviewBean;
	}

	public User_groupInterface getUser_groupBean() {
		return this.user_groupBean;
	}

	public void setUser_groupBean(User_groupInterface userGroupBean) {
		this.user_groupBean = userGroupBean;
	}

	public GroupInterface getGroupBean() {
		return this.groupBean;
	}

	public void setGroupBean(GroupInterface groupBean) {
		this.groupBean = groupBean;
	}

	public UserloginManageInterface getUserloginManageBean() {
		return this.UserloginManageBean;
	}

	public void setUserloginManageBean(
			UserloginManageInterface userloginManageBean) {
		this.UserloginManageBean = userloginManageBean;
	}

	public void initServletContextObject() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
	}

	// ��ȡ������Ҫ�������Ļ
	public String doGetAllCheckAct() {
		initServletContextObject();
		List<GroupActVO> actsVO = new ArrayList<GroupActVO>();
		actsVO = accountingviewBean.doGetAllCheckValidateActs();
		System.out.println("the doGetAllGroupAct get success");
		session.setAttribute("acts", actsVO);

		return "doGetAllAct";
	}

	// ��ȡĳ����Ҫ��check�������ϸ��
	public String doshowCheckDetails() {
		initServletContextObject();
		List<GroupActVO> groupactsVO = new ArrayList<GroupActVO>();
		groupactsVO = (List<GroupActVO>) session.getAttribute("acts");
		int oneactId = Integer.parseInt(request.getParameter("actId"));
		for (int i = 0; i < groupactsVO.size(); i++) {
			int actId = groupactsVO.get(i).getActId();
			if (oneactId == actId) {
				request.setAttribute("act", groupactsVO.get(i));
			}
		}
		request.setAttribute("actId", oneactId);

		return "ShowCheckDetails";
	}

	// ��ȡ������Ҫ��validate�Ļ��ϸ��
	public String doshowValidateDetails() {
		initServletContextObject();
		GroupActVO groupactVO = new GroupActVO();
		int oneactId = Integer.parseInt(request.getParameter("actId"));
		groupactVO = accountingviewBean.doGetAllValidateDetails(oneactId);
		request.setAttribute("act", groupactVO);
		request.setAttribute("actId", oneactId);

		return "ShowValidateDetails";
	}

	// check��ǰ����޸Ļ��state��approved����disapproved��
	public String doCheckAct() {
		String updateMessage = null;
		initServletContextObject();
		int oneactId = (Integer) session.getAttribute("checkedActId");
		ActivitiesVO checkAct = new ActivitiesVO();
		checkAct = actsBean.doGetOneActById(oneactId);
		String checkState = getCheckState();
		checkAct.setState(checkState);
		checkAct.setComment(getComment());
		updateMessage = actsBean.doUpdateOneAct(checkAct);

		// ����������Ҫ��check��validate������Ϣ������
		int newCheck = 0;
		ArrayList<ActivitiesVO> allActs = actsBean.doGetAllActivity();
		for (int i = 0; i < allActs.size(); i++) {
			if (allActs.get(i).getState().equals("draft")
					|| allActs.get(i).getState().equals("tobevalidate")) {
				newCheck += 1;
			}
		}
		session.setAttribute("newCheck", newCheck);

		// ����ʾ���л��ҳ�����
		initServletContextObject();
		List<GroupActVO> actsVO = new ArrayList<GroupActVO>();
		actsVO = accountingviewBean.doGetAllCheckValidateActs();
		session.setAttribute("acts", actsVO);
		return "doGetAllAct";

	}

	// validate��ǰ�����״̬��tobevalidate�޸ĳ�validate
	// ���ҽ������û���spending����
	public String doValidateAct() {
		String updateMessage = null;
		initServletContextObject();
		int oneactId = (Integer) session.getAttribute("validateActId");
		ActivitiesVO validateAct = new ActivitiesVO();
		validateAct = actsBean.doGetOneActById(oneactId);
		String checkState = getCheckState();
		validateAct.setState("validate");
		validateAct.setComment(getComment());
		updateMessage = actsBean.doUpdateOneAct(validateAct);

		// ����spending
		GroupActVO groupactVO = new GroupActVO();
		groupactVO = accountingviewBean.doGetAllValidateDetails(oneactId);
		for (int i = 0; i < groupactVO.getMemberInVO().size(); i++) {
			Integer userId = groupactVO.getMemberInVO().get(i).getUserId();
			Float spending = groupactVO.getMemberInVO().get(i).getConsumption();
			UserloginVO oneUserVO = new UserloginVO();
			oneUserVO = UserloginManageBean.dogetOneUserInfoByUserId(userId);
			oneUserVO.setSpending(oneUserVO.getSpending() + spending);
			UserloginManageBean.doUpdateOneUserInfo(oneUserVO);
		}

		// ����������Ҫ��check��validate������Ϣ������
		int newCheck = 0;
		ArrayList<ActivitiesVO> allActs = actsBean.doGetAllActivity();
		for (int i = 0; i < allActs.size(); i++) {
			if (allActs.get(i).getState().equals("draft")
					|| allActs.get(i).getState().equals("tobevalidate")) {
				newCheck += 1;
			}
		}
		session.setAttribute("newCheck", newCheck);

		// ����ʾ���л��ҳ�����
		initServletContextObject();
		List<GroupActVO> actsVO = new ArrayList<GroupActVO>();
		actsVO = accountingviewBean.doGetAllCheckValidateActs();
		session.setAttribute("acts", actsVO);
		return "doGetAllAct";
	}

	// ͨ��ѡ��year�ķ�ʽ��ʾ�����û���һ��Ļ�������������ʾ�Ѿ���validate�Ļ��
	public String doshowAllActsByYear() {
		initServletContextObject();
		Integer year = Integer.parseInt(request.getParameter("year"));
		List<UserGroupCostVO> allUserGroupCostVO = new ArrayList<UserGroupCostVO>();
		allUserGroupCostVO = accountingviewBean.doGetAllActsByYear(year);
		session.setAttribute("allUserGroupCost", allUserGroupCostVO);

		return "doShowActByYear";
	}

	// ͨ��ѡ��group�ķ�ʽ��ʾ����group��һ��Ļ�������������ʾ�Ѿ���validate�Ļ��
	public String doshowAllActsByGroup() {
		initServletContextObject();
		List<GroupActVO> groupactsVO = new ArrayList<GroupActVO>();
		String groupName = request.getParameter("groupname");
		GroupVO group = groupBean.dogetOneGroupByName(groupName);
		groupactsVO = accountingviewBean.doGetAllActsByGroupId(group
				.getGroupId());
		session.setAttribute("groupacts", groupactsVO);

		return "doShowActByGroup";

	}

	// ��ʾgroup�л��ϸ��
	public String doshowActDetailsInGroup() {
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

		return "doShowActByGroupDetails";
	}

	// �������Ե�get��set����
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

	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
