package com.tu.user.act.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.user.act.dao.User_act;
import com.tu.user.act.dao.User_actDAOInterface;

public class User_actImple extends Observable implements User_actInterface {
	
	private static final Log LOG = LogFactory.getLog(User_actImple.class);
	private User_actDAOInterface user_actDAO = null;

	public User_actDAOInterface getUser_actDAO() {
		return user_actDAO;
	}

	public void setUser_actDAO(User_actDAOInterface userActDAO) {
		user_actDAO = userActDAO;
	}

	public User_actImple() {
	}

	// 通过userId和ActId来获取特定user_act对象
	public User_actVO doGetOneActById(Integer userId, Integer actId) {
		User_actVO userActVO = new User_actVO();
		User_act userActPO = user_actDAO.findByUserIdAndActId(userId, actId);
		try {
			BeanUtils.copyProperties(userActVO, userActPO);

		} catch (IllegalAccessException e) {
			LOG.error(" there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException");
		}

		return userActVO;
	}

	// 添加一个user_act对象
	public String doAddOneUser_act(User_actVO oneUserActVO) {
		String addMessage = null;
		User_act userActPO = new User_act();

		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(userActPO, oneUserActVO);
		} catch (IllegalAccessException e) {
			LOG.error("在MaterialImple类的doAddOneMaterial方法中利用BeanUtils类进行对象拷贝时出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			LOG.error("在MaterialImple类的doAddOneMaterial方法中利用BeanUtils类进行对象拷贝时出现了InvocationTargetException异常");
		}
		try {
			user_actDAO.save(userActPO);
			addMessage = "add User_act success!";

		} catch (Exception e) {
			addMessage = e.toString();
		}
		return addMessage;
	}

	// 删除一个user_act对象
	public String doDeleteOneUser_act(Integer userId, Integer actId) {
		String okOrNot = null;
		User_act userActPO = user_actDAO.findByUserIdAndActId(userId, actId);
		try {
			if (userActPO != null) {
				user_actDAO.delete(userActPO);
				okOrNot = "delete success!";
			} else {
				okOrNot = "delete fail!";
			}

		} catch (Exception e) {
			okOrNot = e.toString();
		}
		return okOrNot;

	}

	// 更新一个user_act对象
	public void doUpdateOneUser_act(User_actVO userActVO) {
		Integer actId = userActVO.getActId();
		Integer userId = userActVO.getUserId();
		User_act userActPO = user_actDAO.findByUserIdAndActId(userId, actId);
		String okOrNot = null;
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(userActPO, userActVO);

		} catch (IllegalAccessException e) {
			LOG.error("there is a IllegalAccessException while copy act in doUpdateOneact.");
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException while copy act in doUpdateOneact.");
		}
		try {
			user_actDAO.merge(userActPO);
			okOrNot = "merge success!";
		} catch (Exception e) {
			okOrNot = e.toString();
		}
	}

}
