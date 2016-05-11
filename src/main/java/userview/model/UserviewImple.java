package userview.model;

import group.dao.Group;
import group.dao.GroupDAOInterface;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

import messages.dao.Messages;
import messages.dao.MessagesDAOInterface;

import org.apache.commons.beanutils.BeanUtils;

import activities.dao.Activities;
import activities.dao.ActivitiesDAOInterface;

import userlogin.dao.Userlogin;
import userlogin.dao.UserloginDAOInterface;
import userlogin.model.UserloginVO;
import user_act.dao.User_act;
import user_act.dao.User_actDAOInterface;
import user_group.dao.User_group;
import user_group.dao.User_groupDAOInterface;
import user_msg.dao.User_msg;
import user_msg.dao.User_msgDAOInterface;

public class UserviewImple extends Observable implements UserviewInterface {

	private User_groupDAOInterface user_groupDAO = null;
	private UserloginDAOInterface userloginDAO = null;
	private GroupDAOInterface groupDAO = null;
	private ActivitiesDAOInterface actsDAO = null;
	private User_actDAOInterface user_actDAO = null;
	private User_msgDAOInterface user_msgDAO = null;
	private MessagesDAOInterface msgDAO = null;

	// ���췽��
	public UserviewImple() {

	}

	public void setUser_groupDAO(User_groupDAOInterface user_groupDAO) {
		this.user_groupDAO = user_groupDAO;
	}

	public void setUserloginDAO(UserloginDAOInterface userloginDAO) {
		this.userloginDAO = userloginDAO;
	}

	public void setGroupDAO(GroupDAOInterface groupDAO) {
		this.groupDAO = groupDAO;
	}

	public void setActsDAO(ActivitiesDAOInterface actsDAO) {
		this.actsDAO = actsDAO;
	}

	public void setUser_actDAO(User_actDAOInterface userActDAO) {
		this.user_actDAO = userActDAO;
	}

	public User_msgDAOInterface getUser_msgDAO() {
		return this.user_msgDAO;
	}

	public void setUser_msgDAO(User_msgDAOInterface userMsgDAO) {
		this.user_msgDAO = userMsgDAO;
	}

	public MessagesDAOInterface getMsgDAO() {
		return this.msgDAO;
	}

	public void setMsgDAO(MessagesDAOInterface msgDAO) {
		this.msgDAO = msgDAO;
	}

	public User_groupDAOInterface getUser_groupDAO() {
		return this.user_groupDAO;
	}

	public UserloginDAOInterface getUserloginDAO() {
		return this.userloginDAO;
	}

	public GroupDAOInterface getGroupDAO() {
		return this.groupDAO;
	}

	public ActivitiesDAOInterface getActsDAO() {
		return this.actsDAO;
	}

	public User_actDAOInterface getUser_actDAO() {
		return this.user_actDAO;
	}

	// ��ȡ�����������û���Ϣ
	public UserviewVO doGetOneUserviewInfoByUserId(Integer userId) {
		Userlogin oneuserloginPO = new Userlogin();
		List<User_group> oneUser_groupPO = new ArrayList<User_group>();
		ArrayList onegroupPO = new ArrayList();
		UserviewVO oneuserviewPO = new UserviewVO();

		try {
			oneuserloginPO = userloginDAO.findById(userId);
			System.out.println("oneuserloginPO ID get success"
					+ oneuserloginPO.getUserId());
			oneUser_groupPO = user_groupDAO.findByUserId(userId);

			for (int i = 0; i < oneUser_groupPO.size(); i++) {
				onegroupPO.add(groupDAO.findById(
						oneUser_groupPO.get(i).getGroupId()).getGroupName());

			}

			oneuserviewPO.setUserId(oneuserloginPO.getUserId());
			oneuserviewPO.setUserName(oneuserloginPO.getUserName());
			oneuserviewPO.setUserPassword(oneuserloginPO.getUserPassword());
			oneuserviewPO.setGroupName(onegroupPO);
			oneuserviewPO.setUserDept(oneuserloginPO.getUserDept());
			oneuserviewPO.setIn_date(oneuserloginPO.getIn_date());
			oneuserviewPO.setSpending(oneuserloginPO.getSpending());
			oneuserviewPO.setQuota(oneuserloginPO.getQuota());
			oneuserviewPO.setRemaining(oneuserloginPO.getQuota()
					- oneuserloginPO.getSpending());

		} catch (Exception e) {
			System.out.println("�������µĴ���" + e);
		}
		System.out.println("���������ݷ��ʲ���AuthorPO:"
				+ oneuserloginPO.getUserName());

		UserviewVO oneuserviewVO = new UserviewVO();
		try { // ����Bean������ʵ�ּ򵥵ؿ���
			BeanUtils.copyProperties(oneuserviewVO, oneuserviewPO);
			System.out.println("�����׼���ô������Ʋ��userviewVO:"
					+ oneuserviewVO.getUserId());
		} catch (IllegalAccessException e) {
			System.out
					.println("��userviewImple���doGetOneUserviewInfoByUserId����������BeanUtils����ж��󿽱�ʱ������IllegalAccessException�쳣");
		} catch (InvocationTargetException e) {
			System.out
					.println("��userviewImple���doGetOneUserviewInfoByUserId����������BeanUtils����ж��󿽱�ʱ������InvocationTargetException�쳣");
		}
		return oneuserviewVO;

	}

	// ͨ���û�Id��ȡ�����û�������Ļ
	public List<UseractsVO> doGetAllUserActsByUserId(Integer userId,
			Integer year) {
		System.out.println("�����ӿ��Ʋ���AuthorId:" + userId);

		List<User_act> user_act = new ArrayList<User_act>();

		List<UseractsVO> oneuseractsVO = new ArrayList<UseractsVO>();

		user_act = user_actDAO.findByUserId(userId);// ͨ��userId���������û�������Ļ��actId

		for (int i = 0; i < user_act.size(); i++) {
			Activities actsPO = new Activities();
			Group groupPO = new Group();

			// ����һ��UseractsVOʵ������������û�����Ļ����������
			// ���������Ŀ�ľ���ͨ��group��user_act��activities�⼸����
			// ��Ĺ�������ȡ����UseractsVO�����Ե�ֵ
			UseractsVO UseractsPO = new UseractsVO();

			actsPO = actsDAO.findById(user_act.get(i).getActId());
			// ͨ��user_act�е�actId����ActsPOʵ��

			groupPO = groupDAO.findById(actsPO.getGroupId());
			// ͨ��ActsPO�е�GroupId����groupPOSʵ��

			Calendar cal = Calendar.getInstance();
			cal.setTime(actsPO.getActDate());

			if (cal.get(Calendar.YEAR) == year) {
				UseractsPO.setUserId(userId);
				// ����UseractsVO�е�����UserId

				UseractsPO.setActId(user_act.get(i).getActId());
				// ͨ��user_act�е����ԣ�����UseractsVO�е�actId

				UseractsPO.setParticipaterNO(user_act.get(i)
						.getParticipatorNO());
				// ͨ��user_act�е����ԣ�����UseractsVO�е�ParticipaterNO

				UseractsPO.setConsumption(user_act.get(i).getConsumption());
				// ͨ��user_act�е����ԣ�����UseractsVO�е�consumption

				UseractsPO.setRemark(user_act.get(i).getRemark());
				// ͨ��user_act�е����ԣ�����UseractsVO�е�Remark

				UseractsPO.setActName(actsPO.getActName());
				// ͨ��ActsPO�е����ԣ�����UseractsVO�е�actName

				UseractsPO.setActMoney(actsPO.getActMoney());
				// ͨ��ActsPO�е����ԣ�����UseractsVO�е�actMoney

				UseractsPO.setState(actsPO.getState());
				// ͨ��ActsPO�е����ԣ�����UseractsVO�е�actState

				UseractsPO.setDescription(actsPO.getDescription());
				// ͨ��ActsPO�е����ԣ�����UseractsVO�е�description

				// DateFormat df1 = DateFormat.getDateInstance();//���ڸ�ʽ����ȷ����
				// System.out.println(df1.format(actsPO.getActDate()));
				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
				UseractsPO.setActDate(formatter.format(actsPO.getActDate()));
				// ͨ��ActsPO�е����ԣ�����UseractsVO�е�actDate

				UseractsPO.setGroup(groupPO.getGroupName());
				// ͨ��groupPOS�е����ԣ�����UseractsVO�е�groupName

				oneuseractsVO.add(UseractsPO);
			}

		}
		List<UseractsVO> InverseoneuseractsVO = new ArrayList<UseractsVO>();
		for (int j = 0; j < oneuseractsVO.size(); j++) {
			InverseoneuseractsVO.add(oneuseractsVO.get(oneuseractsVO.size() - j
					- 1));
		}
		return InverseoneuseractsVO;
	}

	// ��ȡ�û�������messages
	public ArrayList<User_msgVO> dogetMessages(Integer userId) {
		ArrayList<User_msgVO> user_msgsVO = new ArrayList<User_msgVO>();
		ArrayList<User_msg> user_msg = new ArrayList<User_msg>();
		user_msg = (ArrayList<User_msg>) user_msgDAO.findMsgByUserId(userId);
		for (int i = 0; i < user_msg.size(); i++) {
			Integer oneMsgId = ((User_msg) user_msg.get(i)).getMsgId();
			Messages oneMessage = msgDAO.findById(oneMsgId);
			User_msgVO oneUser_msgVO = new User_msgVO();
			try { // ����Bean������ʵ�ּ򵥵ؿ���
				BeanUtils.copyProperties(oneUser_msgVO, oneMessage);
			} catch (IllegalAccessException e) {
				System.out
						.println("there is a IllegalAccessException while copy oneMessage to oneUser_msgVO.");
			} catch (InvocationTargetException e) {
				System.out
						.println("there is a InvocationTargetException while copy oneMessage to oneUser_msgVO.");
			}
			oneUser_msgVO.setReadState(((User_msg) user_msg.get(i))
					.getReadState());
			Group group = groupDAO.findById(oneMessage.getGroupId());
			oneUser_msgVO.setGroupName(group.getGroupName());
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			String daterange = formatter
					.format(oneMessage.getEnrollStartDate())
					+ " - "
					+ formatter.format(oneMessage.getEnrollEndDate());
			oneUser_msgVO.setDateRange(daterange);
			oneUser_msgVO.setActDate(formatter.format(oneMessage.getActDate()));
			user_msgsVO.add(oneUser_msgVO);
		}
		ArrayList<User_msgVO> Inverseuser_msgsVO = new ArrayList<User_msgVO>();
		for (int j = 0; j < user_msgsVO.size(); j++) {
			Inverseuser_msgsVO.add(user_msgsVO.get(user_msgsVO.size() - j - 1));
		}
		return Inverseuser_msgsVO;

	}

	// �û��޸�����
	public String doupdateOneuserInfo(UserloginVO userInfoVO) {
		Userlogin userInfoPO = new Userlogin();
		Integer userId = userInfoVO.getUserId();
		System.out.println("the modified id is:" + userId);
		userInfoPO = userloginDAO.findById(userId);
		String OkOrNot = null;
		try { // ����Bean������ʵ�ּ򵥵ؿ���
			BeanUtils.copyProperties(userInfoPO, userInfoVO);
			System.out.println("�����:userInfoPO:" + userInfoPO.getUserId()
					+ userInfoPO.getUserName());
		} catch (IllegalAccessException e) {
			System.out
					.println("��userviewImple���doupdateOneuserInfo����������BeanUtils����ж��󿽱�ʱ������IllegalAccessException�쳣");
		} catch (InvocationTargetException e) {
			System.out
					.println("��userviewImple���doupdateOneuserInfo����������BeanUtils����ж��󿽱�ʱ������InvocationTargetException�쳣");
		}
		try {
			userloginDAO.merge(userInfoPO);
			OkOrNot = "modify success!";
		} catch (Exception e) {
			OkOrNot = e.toString();
		}

		return OkOrNot;
	}

}
