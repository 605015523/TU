package userlogin.struts2action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import group.model.GroupVO;
import group.model.GroupInterface;

import activities.model.ActivitiesVO;
import activities.model.ActivitiesInterface;

import leaderview.model.LeaderviewInterface;
import messages.model.MessagesInterface;
import messages.model.MessagesVO;

import user_group.model.User_groupInterface;
import user_msg.dao.User_msg;
import user_msg.model.User_msgInterface;

import userlogin.model.UserloginVO;
import userlogin.model.UserloginManageInterface;

import userview.model.UserviewVO;
import userview.model.UserviewInterface;

public class UserloginManageAction extends ActionSupport {
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private HttpSession session = null;

	UserloginManageInterface UserloginManageBean = null;
	UserviewInterface UserviewBean = null;
	LeaderviewInterface leaderviewBean = null;
	ActivitiesInterface actsBean = null;
	GroupInterface groupBean = null;
	MessagesInterface msgBean = null;
	User_msgInterface user_msgBean = null;
	User_groupInterface user_groupBean = null;

	private Integer userId;
	private String userName;
	private String userPassword;
	private Integer userRole;
	private List<String> groupName;
	private String userDept;
	private Date in_date;
	private Date out_date;
	private Float old_balance;
	private Float quota;
	private Float remaining;
	private String loginMessage;

	public UserloginManageAction() {
	}

	public void initUserlogin() {
		userId = null;
		userName = null;
		userPassword = null;
		userRole = null;
		userDept = null;
		in_date = null;
		out_date = null;
		old_balance = null;
		quota = null;
	}

	public void initServletContextObject() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();

	}

	public void setUserloginManageBean(
			UserloginManageInterface UserloginManageBean) {
		this.UserloginManageBean = UserloginManageBean;
	}

	public UserviewInterface getUserviewBean() {
		return this.UserviewBean;
	}

	public void setUserviewBean(UserviewInterface UserviewBean) {
		this.UserviewBean = UserviewBean;
	}

	public LeaderviewInterface getLeaderviewBean() {
		return leaderviewBean;
	}

	public void setLeaderviewBean(LeaderviewInterface leaderviewBean) {
		this.leaderviewBean = leaderviewBean;
	}

	public void setActsBean(ActivitiesInterface actsBean) {
		this.actsBean = actsBean;
	}

	public GroupInterface getGroupBean() {
		return this.groupBean;
	}

	public void setGroupBean(GroupInterface groupBean) {
		this.groupBean = groupBean;
	}

	public User_msgInterface getUser_msgBean() {
		return this.user_msgBean;
	}

	public void setUser_msgBean(User_msgInterface userMsgBean) {
		this.user_msgBean = userMsgBean;
	}

	public MessagesInterface getMsgBean() {
		return msgBean;
	}

	public void setMsgBean(MessagesInterface msgBean) {
		this.msgBean = msgBean;
	}

	public User_groupInterface getUser_groupBean() {
		return user_groupBean;
	}

	public void setUser_groupBean(User_groupInterface userGroupBean) {
		user_groupBean = userGroupBean;
	}

	public String dologout() {
		initServletContextObject();
		session.invalidate();
		return "logout";
	}

	// �û���¼����action
	public String doLogin() throws ParseException {

		// �û���¼��֤ģ��
		initServletContextObject();
		ArrayList<UserloginVO> knowledgeadministratorVOs = new ArrayList<UserloginVO>();
		knowledgeadministratorVOs = UserloginManageBean.doGetAllUserlogin();
		boolean flag = true;
		for (int i = 0; i < knowledgeadministratorVOs.size(); i++) {
			if (userName.equals(knowledgeadministratorVOs.get(i).getUserName())
					&& userPassword.equals(knowledgeadministratorVOs.get(i)
							.getUserPassword())) {

				userPassword = knowledgeadministratorVOs.get(i)
						.getUserPassword();
				userName = knowledgeadministratorVOs.get(i).getUserName();
				userId = knowledgeadministratorVOs.get(i).getUserId();
				userRole = knowledgeadministratorVOs.get(i).getUserRole();
				flag = true;
				break;
			} else {
				flag = false;
			}
		}
		if (flag == true) {
			// ��¼�ɹ������������̨
			System.out.println("login sucess");

			// ��¼�ɹ�����ȡ�������ݿ��д�ŵĻ���ޣ��Ա��¼����ҳ�Ļ�����˵�ѡȡ����
			initServletContextObject();
			ArrayList<ActivitiesVO> actVOs = new ArrayList<ActivitiesVO>();
			actVOs = actsBean.doGetAllActivity();
			List<Integer> years = new ArrayList();
			for (int i = 0; i < actVOs.size(); i++) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(actsBean.doGetAllActivity().get(i).getActDate());
				if (years.size() == 0) {
					years.add(cal.get(Calendar.YEAR));
				} else {
					Integer j;
					for (j = 0; j < years.size();) {
						if (cal.get(Calendar.YEAR) == years.get(j)) {
							break;
						}
						j++;
					}
					if (j.equals(years.size()) == true) {
						years.add(cal.get(Calendar.YEAR));
						System.out.println("the year" + cal.get(Calendar.YEAR)
								+ "get success");
					}
				}
			}
			// ��¼�ɹ����ж�Ԥ���Ƿ���޸ģ���Ϊ�����7��1�ţ�Ԥ����ԭ�����ϼ�1000
			initServletContextObject();
			UserloginVO thisuser = UserloginManageBean
					.dogetOneUserInfoByUserId(userId);
			Date date = new Date();// ȡ��ǰʱ��
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String year = Integer.toString(cal.get(Calendar.YEAR));
			Date updatedate = new SimpleDateFormat("yyyy-MM-dd").parse(year
					+ "-02-25");
			try {
				if ((thisuser.getIn_date().getTime() - updatedate.getTime() < 0)
						&& updatedate.before(date)
						&& thisuser.getQuota() == 1000) {
					thisuser.setQuota(thisuser.getQuota() + 1000);
					UserloginManageBean.doUpdateOneUserInfo(thisuser);
				}
			} catch (Exception e) {

			}

			// ��¼�ɹ���ʶ�������Ϣ������ȡ����Ϣ����
			for (int i = 0; i < actVOs.size(); i++) {
				ActivitiesVO oneAct = new ActivitiesVO();
				oneAct = actVOs.get(i);
				if (oneAct.getState().equals("publish")) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);
					calendar.add(calendar.DATE, -1);// ��Ϊactdate�м�¼��ʱ���ǵ�������
													// ���ԶԱȹ���ʱ��ʱ�����������������һ��
					date = calendar.getTime(); // ���ʱ���������������һ��Ľ��
					if (oneAct.getActDate().before(date)) {
						oneAct.setState("pending");
						actsBean.doUpdateOneAct(oneAct);
					}
				}

			}

			int newMsg = 0;
			ArrayList<User_msg> user_msgVOs = new ArrayList<User_msg>();
			user_msgVOs = (ArrayList<User_msg>) user_msgBean
					.doGetUserMsg(userId);
			for (int i = 0; i < user_msgVOs.size(); i++) {
				MessagesVO oneMsg = msgBean.doGetOneMsgById(user_msgVOs.get(i)
						.getMsgId());
				ActivitiesVO oneAct = new ActivitiesVO();
				oneAct = actsBean.doGetOneActById(oneMsg.getActId());
				if (oneAct.getState().equals("pending")
						|| oneAct.getState().equals("tobevalidate")
						|| oneAct.getState().equals("validate")) {
					user_msgVOs.get(i).setReadState("read");
					user_msgBean.doUpdateOneUser_msg(user_msgVOs.get(i));

				}
				if (user_msgVOs.get(i).getReadState().equals("new")) {
					newMsg += 1;
				}

			}

			// ��¼�ɹ�����ȡ�û�������Ϣbean
			initServletContextObject();
			UserviewVO userviewVO = new UserviewVO();
			userviewVO = UserviewBean.doGetOneUserviewInfoByUserId(this
					.getUserId());

			// ��½�󣬽��û�һֱ��Ҫ�õ�����Щ��Ϣ�浽session���Ա����ҳ���ʹ��
			session.setAttribute("userId", userId);
			session.setAttribute("userRole", userRole);
			session.setAttribute("userview", userviewVO);
			session.setAttribute("years", years);
			session.setAttribute("newMsg", newMsg);

			// ��½�ɹ�����Ϊ�鳤ִ�����»
			if (userRole.equals(1)) {

				// ��ȡ����С����Ϣ���Ա�����ʹ��
				initServletContextObject();
				GroupVO group = new GroupVO();
				group = groupBean.dogetOneGroup(userId);

				// ��ȡ�����û�Id���Ա���ӻʱ������Ϣ���û�
				initServletContextObject();
				ArrayList<Integer> allMemberId = new ArrayList<Integer>();
				for (int m = 0; m < knowledgeadministratorVOs.size(); m++) {
					Integer userId = knowledgeadministratorVOs.get(m)
							.getUserId();
					allMemberId.add(userId);
				}

				// ���ոջ�ȡ�������ݴ浽session��
				session.setAttribute("group", group);
				session.setAttribute("allMemberId", allMemberId);
			}

			// ��Ϊ����С���Ա��ִ�����²���
			if (userRole.equals(2)) {
				initServletContextObject();
				int newCheck = 0;
				for (int k = 0; k < actVOs.size(); k++) {
					if (actVOs.get(k).getState().equals("draft")
							|| actVOs.get(k).getState().equals("tobevalidate")) {
						newCheck += 1;
					}
				}
				ArrayList<Integer> allUserId = new ArrayList<Integer>();
				for (int i = 0; i < knowledgeadministratorVOs.size(); i++) {
					allUserId.add(knowledgeadministratorVOs.get(i).getUserId());
				}

				ArrayList<String> groupsName = new ArrayList<String>();
				ArrayList<GroupVO> allgroup = groupBean.dogetAllGroup();
				for (int i = 0; i < allgroup.size(); i++) {
					groupsName.add(allgroup.get(i).getGroupName());
				}
				session.setAttribute("allserId", allUserId);
				session.setAttribute("newCheck", newCheck);
				session.setAttribute("groupsName", groupsName);
			}

			return "userloginSuccess";

		} else {

			// ��¼���ɹ���ִ�еĲ���
			System.out.println("login fail");
			loginMessage = "Incorrect ID or password. Please re-enter.";
			session.setAttribute("userName", userName);
			request.setAttribute("loginMessage", loginMessage);
			return "loginFail";
		}
	}

	// getter, setters
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

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Integer userRole) {
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

	public Date getOut_date() {
		return this.out_date;
	}

	public void setOut_date(Date outDate) {
		this.out_date = outDate;
	}

	public Float getOld_balance() {
		return this.old_balance;
	}

	public void setOld_balance(Float oldBalance) {
		this.old_balance = oldBalance;
	}

	public Float getQuota() {
		return this.quota;
	}

	public void setQuota(Float quota) {
		this.quota = quota;
	}

	public List<String> getGroupName() {
		return this.groupName;
	}

	public void setGroupName(List<String> groupName) {
		this.groupName = groupName;
	}

	public Float getRemaining() {
		return this.remaining;
	}

	public void setRemaining(Float remaining) {
		this.remaining = remaining;
	}

}
