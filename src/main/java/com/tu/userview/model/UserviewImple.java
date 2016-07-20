package com.tu.userview.model;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.activities.dao.Activity;
import com.tu.activities.dao.ActivityDAOInterface;
import com.tu.group.dao.Group;
import com.tu.group.dao.GroupDAOInterface;
import com.tu.messages.dao.Messages;
import com.tu.messages.dao.MessagesDAOInterface;
import com.tu.user.act.dao.User_act;
import com.tu.user.act.dao.User_actDAOInterface;
import com.tu.user.group.dao.User_group;
import com.tu.user.group.dao.User_groupDAOInterface;
import com.tu.user.msg.dao.UserMsg;
import com.tu.user.msg.dao.UserMsgDAOInterface;
import com.tu.userlogin.dao.Userlogin;
import com.tu.userlogin.dao.UserloginDAOInterface;
import com.tu.userlogin.model.UserloginVO;

public class UserviewImple extends Observable implements UserviewInterface {

	private static final Log LOG = LogFactory.getLog(UserviewImple.class);
	private User_groupDAOInterface user_groupDAO = null;
	private UserloginDAOInterface userloginDAO = null;
	private GroupDAOInterface groupDAO = null;
	private ActivityDAOInterface actsDAO = null;
	private User_actDAOInterface user_actDAO = null;
	private UserMsgDAOInterface userMsgDAO = null;
	private MessagesDAOInterface msgDAO = null;

	// 构造方法
	public UserviewImple() {

	}

	public void setUser_groupDAO(User_groupDAOInterface userGroupDAO) {
		this.user_groupDAO = userGroupDAO;
	}

	public void setUserloginDAO(UserloginDAOInterface userloginDAO) {
		this.userloginDAO = userloginDAO;
	}

	public void setGroupDAO(GroupDAOInterface groupDAO) {
		this.groupDAO = groupDAO;
	}

	public void setActsDAO(ActivityDAOInterface actsDAO) {
		this.actsDAO = actsDAO;
	}

	public void setUser_actDAO(User_actDAOInterface userActDAO) {
		this.user_actDAO = userActDAO;
	}

	public UserMsgDAOInterface getUserMsgDAO() {
		return this.userMsgDAO;
	}

	public void setUserMsgDAO(UserMsgDAOInterface userMsgDAO) {
		this.userMsgDAO = userMsgDAO;
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

	public ActivityDAOInterface getActsDAO() {
		return this.actsDAO;
	}

	public User_actDAOInterface getUser_actDAO() {
		return this.user_actDAO;
	}

	// 获取主界面所有用户信息
	@Override
	public UserviewVO doGetOneUserviewInfoByUserId(Integer userId) {
		List<String> onegroupPO = new ArrayList<String>();
		UserviewVO oneuserviewPO = new UserviewVO();

		try {
			Userlogin oneuserloginPO = userloginDAO.findById(userId);
			LOG.info("oneuserloginPO ID get success"
					+ oneuserloginPO.getUserId());
			List<User_group> oneUserGroupPO = user_groupDAO.findByUserId(userId);

			for (int i = 0; i < oneUserGroupPO.size(); i++) {
				onegroupPO.add(groupDAO.findById(
						oneUserGroupPO.get(i).getGroupId()).getGroupName());

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
			
			LOG.info("服务层从数据访问层获得AuthorPO:"
					+ oneuserloginPO.getUserName());

		} catch (Exception e) {
			LOG.error("出现如下的错误：" + e);
		}
		

		UserviewVO oneuserviewVO = new UserviewVO();
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneuserviewVO, oneuserviewPO);
			LOG.info("服务层准备好传给控制层的userviewVO:"
					+ oneuserviewVO.getUserId());
		} catch (IllegalAccessException e) {
			LOG.error("在userviewImple类的doGetOneUserviewInfoByUserId方法中利用BeanUtils类进行对象拷贝时出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			LOG.error("在userviewImple类的doGetOneUserviewInfoByUserId方法中利用BeanUtils类进行对象拷贝时出现了InvocationTargetException异常");
		}
		return oneuserviewVO;

	}

	// 通过用户Id获取所有用户所参与的活动
	@Override
	public List<UseractsVO> doGetAllUserActsByUserId(Integer userId,
			Integer year) {
		LOG.info("服务层从控制层获得AuthorId:" + userId);

		List<UseractsVO> oneuseractsVO = new ArrayList<UseractsVO>();
		List<User_act> userActs = user_actDAO.findByUserId(userId);// 通过userId遍历所有用户参与过的活动的actId

		for (int i = 0; i < userActs.size(); i++) {
			// 建立一个UseractsVO实例，里面包含用户参与的活动的所有属性
			// 后续步骤的目的就是通过group、user_act、activities这几个表
			// 间的关联，获取所有UseractsVO中属性的值
			UseractsVO useractsPO = new UseractsVO();

			Activity actsPO = actsDAO.findById(userActs.get(i).getActId());
			// 通过user_act中的actId查找ActsPO实例

			Group groupPO = groupDAO.findById(actsPO.getGroupId());
			// 通过ActsPO中的GroupId查找groupPOS实例

			Calendar cal = Calendar.getInstance();
			cal.setTime(actsPO.getActDate());

			if (cal.get(Calendar.YEAR) == year) {
				useractsPO.setUserId(userId);
				// 设置UseractsVO中的所有UserId

				useractsPO.setActId(userActs.get(i).getActId());
				// 通过user_act中的属性，设置UseractsVO中的actId

				useractsPO.setParticipaterNO(userActs.get(i)
						.getParticipatorNO());
				// 通过user_act中的属性，设置UseractsVO中的ParticipaterNO

				useractsPO.setConsumption(userActs.get(i).getConsumption());
				// 通过user_act中的属性，设置UseractsVO中的consumption

				useractsPO.setRemark(userActs.get(i).getRemark());
				// 通过user_act中的属性，设置UseractsVO中的Remark

				useractsPO.setActName(actsPO.getActName());
				// 通过ActsPO中的属性，设置UseractsVO中的actName

				useractsPO.setActMoney(actsPO.getActMoney());
				// 通过ActsPO中的属性，设置UseractsVO中的actMoney

				useractsPO.setState(actsPO.getState());
				// 通过ActsPO中的属性，设置UseractsVO中的actState

				useractsPO.setDescription(actsPO.getDescription());
				// 通过ActsPO中的属性，设置UseractsVO中的description

				// DateFormat df1 = DateFormat.getDateInstance();//日期格式，精确到日
				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
				useractsPO.setActDate(formatter.format(actsPO.getActDate()));
				// 通过ActsPO中的属性，设置UseractsVO中的actDate

				useractsPO.setGroup(groupPO.getGroupName());
				// 通过groupPOS中的属性，设置UseractsVO中的groupName

				oneuseractsVO.add(useractsPO);
			}

		}
		List<UseractsVO> inverseoneUseractsVO = new ArrayList<UseractsVO>();
		for (int j = oneuseractsVO.size()-1; j >=0 ; j--) {
			inverseoneUseractsVO.add(oneuseractsVO.get(j));
		}
		return inverseoneUseractsVO;
	}

	// 获取用户的所有messages
	@Override
	public List<UserMsgVO> dogetMessages(Integer userId) {
		List<UserMsgVO> userMsgsVO = new ArrayList<UserMsgVO>();
		List<UserMsg> userMsgs = userMsgDAO.findMsgByUserId(userId);
		
		for (int i = 0; i < userMsgs.size(); i++) {
			Integer oneMsgId = userMsgs.get(i).getMsgId();
			Messages oneMessage = msgDAO.findById(oneMsgId);
			UserMsgVO oneUserMsgVO = new UserMsgVO();
			try { // 利用Bean拷贝类实现简单地拷贝
				BeanUtils.copyProperties(oneUserMsgVO, oneMessage);
			} catch (IllegalAccessException e) {
				LOG.error("there is a IllegalAccessException while copy oneMessage to oneUserMsgVO.");
			} catch (InvocationTargetException e) {
				LOG.error("there is a InvocationTargetException while copy oneMessage to oneUserMsgVO.");
			}
			oneUserMsgVO.setReadState(((UserMsg) userMsgs.get(i))
					.getReadState());
			Group group = groupDAO.findById(oneMessage.getGroupId());
			oneUserMsgVO.setGroupName(group.getGroupName());
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			String daterange = formatter
					.format(oneMessage.getEnrollStartDate())
					+ " - "
					+ formatter.format(oneMessage.getEnrollEndDate());
			oneUserMsgVO.setDateRange(daterange);
			oneUserMsgVO.setActDate(formatter.format(oneMessage.getActDate()));
			userMsgsVO.add(oneUserMsgVO);
		}
		List<UserMsgVO> inverseuserMsgsVO = new ArrayList<UserMsgVO>();
		for (int j = userMsgsVO.size()-1; j >= 0; j--) {
			inverseuserMsgsVO.add(userMsgsVO.get(j));
		}
		return inverseuserMsgsVO;

	}

	// 用户修改密码
	@Override
	public String doupdateOneuserInfo(UserloginVO userInfoVO) {
		Integer userId = userInfoVO.getUserId();
		LOG.info("the modified id is:" + userId);
		Userlogin userInfoPO = userloginDAO.findById(userId);
		String okOrNot = null;
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(userInfoPO, userInfoVO);
			LOG.info("服务层:userInfoPO:" + userInfoPO.getUserId()
					+ userInfoPO.getUserName());
		} catch (IllegalAccessException e) {
			LOG.error("在userviewImple类的doupdateOneuserInfo方法中利用BeanUtils类进行对象拷贝时出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			LOG.error("在userviewImple类的doupdateOneuserInfo方法中利用BeanUtils类进行对象拷贝时出现了InvocationTargetException异常");
		}
		try {
			userloginDAO.merge(userInfoPO);
			okOrNot = "modify success!";
		} catch (Exception e) {
			okOrNot = e.toString();
		}

		return okOrNot;
	}

}
