package com.tu.model.userview;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.activities.Activity;
import com.tu.dao.activities.ActivityDAOInterface;
import com.tu.dao.group.GroupDAOInterface;
import com.tu.dao.user.act.UserAct;
import com.tu.dao.user.act.UserActDAOInterface;
import com.tu.dao.user.group.UserGroup;
import com.tu.dao.user.group.UserGroupDAOInterface;
import com.tu.dao.userlogin.Userlogin;
import com.tu.dao.userlogin.UserloginDAOInterface;
import com.tu.mapper.DAOModelMapper;
import com.tu.model.userlogin.UserloginVO;

public class UserviewImple extends Observable implements UserviewInterface {

	private static final Log LOG = LogFactory.getLog(UserviewImple.class);
	private UserGroupDAOInterface userGroupDAO = null;
	private UserloginDAOInterface userloginDAO = null;
	private GroupDAOInterface groupDAO = null;
	private ActivityDAOInterface actsDAO = null;
	private UserActDAOInterface userActDAO = null;
	private DAOModelMapper daoModelMapper;

	// 构造方法
	public UserviewImple() {
		// do nothing
	}

	public void setUserGroupDAO(UserGroupDAOInterface userGroupDAO) {
		this.userGroupDAO = userGroupDAO;
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

	public void setUserActDAO(UserActDAOInterface userActDAO) {
		this.userActDAO = userActDAO;
	}

	public UserGroupDAOInterface getUserGroupDAO() {
		return this.userGroupDAO;
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

	public UserActDAOInterface getUserActDAO() {
		return this.userActDAO;
	}

	// 获取主界面所有用户信息
	@Override
	public UserviewVO doGetOneUserviewInfoByUserId(Integer userId) {
		List<String> onegroupPO = new ArrayList<String>();
		UserviewVO oneuserviewPO = new UserviewVO();

		try {
			Userlogin oneuserloginPO = userloginDAO.findById(userId);
			LOG.info("oneuserloginPO ID get success:"
					+ oneuserloginPO.getUserId());
			List<UserGroup> oneUserGroupPO = userGroupDAO.findByUserId(userId);

			for (int i = 0; i < oneUserGroupPO.size(); i++) {
				onegroupPO.add(groupDAO.findById(
						oneUserGroupPO.get(i).getGroupId()).getGroupName());

			}

			oneuserviewPO.setUserId(oneuserloginPO.getUserId());
			oneuserviewPO.setUserName(oneuserloginPO.getUserName());
			oneuserviewPO.setGroupName(onegroupPO);
			oneuserviewPO.setUserDept(oneuserloginPO.getUserDept());
			oneuserviewPO.setInDate(oneuserloginPO.getInDate());
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
	public List<UserActDetailedVO> doGetAllUserActsByUserId(Integer userId,
			Integer year) {
		LOG.info("服务层从控制层获得AuthorId:" + userId);

		List<UserActDetailedVO> oneuseractsVO = new ArrayList<UserActDetailedVO>();
		List<UserAct> userActs = userActDAO.findByUserId(userId);// 通过userId遍历所有用户参与过的活动的actId
		Calendar cal = Calendar.getInstance();
		
		for (UserAct userAct : userActs) {
			// 建立一个UseractsVO实例，里面包含用户参与的活动的所有属性
			// 后续步骤的目的就是通过group、userAct、activities这几个表
			// 间的关联，获取所有UseractsVO中属性的值

			Activity actsPO = actsDAO.findById(userAct.getActId());
			cal.setTime(actsPO.getActDate());

			if (cal.get(Calendar.YEAR) == year) {
				UserActDetailedVO useractsPO = daoModelMapper.createUserActDetailed(userAct, actsPO);
				oneuseractsVO.add(useractsPO);
			}
		}
		Collections.reverse(oneuseractsVO);
		
		return oneuseractsVO;
	}
	

	// 用户修改密码
	@Override
	public String doUpdateOneuserInfo(UserloginVO userInfoVO) {
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

	@Override
	public UserActDetailedVO doGetUserActsByUserIdAndActId(Integer userId,
			Integer actId) {
		UserAct userAct = userActDAO.findByUserIdAndActId(userId, actId);
		Activity actsPO = actsDAO.findById(userAct.getActId());
		
		return daoModelMapper.createUserActDetailed(userAct, actsPO);
	}

	public DAOModelMapper getDaoModelMapper() {
		return daoModelMapper;
	}

	public void setDaoModelMapper(DAOModelMapper daoModelMapper) {
		this.daoModelMapper = daoModelMapper;
	}

}
