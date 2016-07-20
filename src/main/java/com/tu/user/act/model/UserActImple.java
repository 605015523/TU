package com.tu.user.act.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.user.act.dao.User_act;
import com.tu.user.act.dao.UseActDAOInterface;

public class UserActImple extends Observable implements UserActInterface {
	
	private static final Log LOG = LogFactory.getLog(UserActImple.class);
	private UseActDAOInterface userActDAO = null;

	public UserActImple() {
		// do nothing
	}
	
	public UseActDAOInterface getUserActDAO() {
		return userActDAO;
	}

	public void setUserActDAO(UseActDAOInterface userActDAO) {
		this.userActDAO = userActDAO;
	}

	// 通过userId和ActId来获取特定user_act对象
	@Override
	public UserActVO doGetOneActById(Integer userId, Integer actId) {
		UserActVO userActVO = new UserActVO();
		User_act userActPO = userActDAO.findByUserIdAndActId(userId, actId);
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
	@Override
	public String doAddOneUser_act(UserActVO oneUserActVO) {
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
			userActDAO.save(userActPO);
			addMessage = "add User_act success!";

		} catch (Exception e) {
			addMessage = e.toString();
		}
		return addMessage;
	}

	// 删除一个user_act对象
	@Override
	public String doDeleteOneUser_act(Integer userId, Integer actId) {
		String okOrNot = null;
		User_act userActPO = userActDAO.findByUserIdAndActId(userId, actId);
		try {
			if (userActPO != null) {
				userActDAO.delete(userActPO);
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
	@Override
	public void doUpdateOneUser_act(UserActVO userActVO) {
		Integer actId = userActVO.getActId();
		Integer userId = userActVO.getUserId();
		User_act userActPO = userActDAO.findByUserIdAndActId(userId, actId);
		String okOrNot = null;
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(userActPO, userActVO);

		} catch (IllegalAccessException e) {
			LOG.error("there is a IllegalAccessException while copy act in doUpdateOneact.");
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException while copy act in doUpdateOneact.");
		}
		try {
			userActDAO.merge(userActPO);
			okOrNot = "merge success!";
		} catch (Exception e) {
			okOrNot = e.toString();
		}
	}

}
