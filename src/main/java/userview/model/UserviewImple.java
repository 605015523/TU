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

	// 构造方法
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

	// 获取主界面所有用户信息
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
			System.out.println("出现如下的错误：" + e);
		}
		System.out.println("服务层从数据访问层获得AuthorPO:"
				+ oneuserloginPO.getUserName());

		UserviewVO oneuserviewVO = new UserviewVO();
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneuserviewVO, oneuserviewPO);
			System.out.println("服务层准备好传给控制层的userviewVO:"
					+ oneuserviewVO.getUserId());
		} catch (IllegalAccessException e) {
			System.out
					.println("在userviewImple类的doGetOneUserviewInfoByUserId方法中利用BeanUtils类进行对象拷贝时出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			System.out
					.println("在userviewImple类的doGetOneUserviewInfoByUserId方法中利用BeanUtils类进行对象拷贝时出现了InvocationTargetException异常");
		}
		return oneuserviewVO;

	}

	// 通过用户Id获取所有用户所参与的活动
	public List<UseractsVO> doGetAllUserActsByUserId(Integer userId,
			Integer year) {
		System.out.println("服务层从控制层获得AuthorId:" + userId);

		List<User_act> user_act = new ArrayList<User_act>();

		List<UseractsVO> oneuseractsVO = new ArrayList<UseractsVO>();

		user_act = user_actDAO.findByUserId(userId);// 通过userId遍历所有用户参与过的活动的actId

		for (int i = 0; i < user_act.size(); i++) {
			Activities actsPO = new Activities();
			Group groupPO = new Group();

			// 建立一个UseractsVO实例，里面包含用户参与的活动的所有属性
			// 后续步骤的目的就是通过group、user_act、activities这几个表
			// 间的关联，获取所有UseractsVO中属性的值
			UseractsVO UseractsPO = new UseractsVO();

			actsPO = actsDAO.findById(user_act.get(i).getActId());
			// 通过user_act中的actId查找ActsPO实例

			groupPO = groupDAO.findById(actsPO.getGroupId());
			// 通过ActsPO中的GroupId查找groupPOS实例

			Calendar cal = Calendar.getInstance();
			cal.setTime(actsPO.getActDate());

			if (cal.get(Calendar.YEAR) == year) {
				UseractsPO.setUserId(userId);
				// 设置UseractsVO中的所有UserId

				UseractsPO.setActId(user_act.get(i).getActId());
				// 通过user_act中的属性，设置UseractsVO中的actId

				UseractsPO.setParticipaterNO(user_act.get(i)
						.getParticipatorNO());
				// 通过user_act中的属性，设置UseractsVO中的ParticipaterNO

				UseractsPO.setConsumption(user_act.get(i).getConsumption());
				// 通过user_act中的属性，设置UseractsVO中的consumption

				UseractsPO.setRemark(user_act.get(i).getRemark());
				// 通过user_act中的属性，设置UseractsVO中的Remark

				UseractsPO.setActName(actsPO.getActName());
				// 通过ActsPO中的属性，设置UseractsVO中的actName

				UseractsPO.setActMoney(actsPO.getActMoney());
				// 通过ActsPO中的属性，设置UseractsVO中的actMoney

				UseractsPO.setState(actsPO.getState());
				// 通过ActsPO中的属性，设置UseractsVO中的actState

				UseractsPO.setDescription(actsPO.getDescription());
				// 通过ActsPO中的属性，设置UseractsVO中的description

				// DateFormat df1 = DateFormat.getDateInstance();//日期格式，精确到日
				// System.out.println(df1.format(actsPO.getActDate()));
				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
				UseractsPO.setActDate(formatter.format(actsPO.getActDate()));
				// 通过ActsPO中的属性，设置UseractsVO中的actDate

				UseractsPO.setGroup(groupPO.getGroupName());
				// 通过groupPOS中的属性，设置UseractsVO中的groupName

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

	// 获取用户的所有messages
	public ArrayList<User_msgVO> dogetMessages(Integer userId) {
		ArrayList<User_msgVO> user_msgsVO = new ArrayList<User_msgVO>();
		ArrayList<User_msg> user_msg = new ArrayList<User_msg>();
		user_msg = (ArrayList<User_msg>) user_msgDAO.findMsgByUserId(userId);
		for (int i = 0; i < user_msg.size(); i++) {
			Integer oneMsgId = ((User_msg) user_msg.get(i)).getMsgId();
			Messages oneMessage = msgDAO.findById(oneMsgId);
			User_msgVO oneUser_msgVO = new User_msgVO();
			try { // 利用Bean拷贝类实现简单地拷贝
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

	// 用户修改密码
	public String doupdateOneuserInfo(UserloginVO userInfoVO) {
		Userlogin userInfoPO = new Userlogin();
		Integer userId = userInfoVO.getUserId();
		System.out.println("the modified id is:" + userId);
		userInfoPO = userloginDAO.findById(userId);
		String OkOrNot = null;
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(userInfoPO, userInfoVO);
			System.out.println("服务层:userInfoPO:" + userInfoPO.getUserId()
					+ userInfoPO.getUserName());
		} catch (IllegalAccessException e) {
			System.out
					.println("在userviewImple类的doupdateOneuserInfo方法中利用BeanUtils类进行对象拷贝时出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			System.out
					.println("在userviewImple类的doupdateOneuserInfo方法中利用BeanUtils类进行对象拷贝时出现了InvocationTargetException异常");
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
