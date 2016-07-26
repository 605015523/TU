package com.tu.model.user.act;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.user.act.UserAct;
import com.tu.dao.user.act.UserActDAOInterface;

public class UserActImple extends Observable implements UserActInterface {
	
	private static final Log LOG = LogFactory.getLog(UserActImple.class);
	private UserActDAOInterface userActDAO = null;

	public UserActImple() {
		// do nothing
	}
	
	public UserActDAOInterface getUserActDAO() {
		return userActDAO;
	}

	public void setUserActDAO(UserActDAOInterface userActDAO) {
		this.userActDAO = userActDAO;
	}

	// 通过userId和ActId来获取特定userAct对象
	@Override
	public UserActVO doGetOneActById(Integer userId, Integer actId) {
		UserActVO userActVO = new UserActVO();
		UserAct userActPO = userActDAO.findByUserIdAndActId(userId, actId);
		try {
			BeanUtils.copyProperties(userActVO, userActPO);

		} catch (IllegalAccessException e) {
			LOG.error(" there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException");
		}

		return userActVO;
	}

	// 添加一个userAct对象
	@Override
	public String doAddOneUserAct(UserActVO oneUserActVO) {
		String addMessage = null;
		UserAct userActPO = new UserAct();

		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(userActPO, oneUserActVO);
		} catch (IllegalAccessException e) {
			LOG.error("在MaterialImple类的doAddOneMaterial方法中利用BeanUtils类进行对象拷贝时出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			LOG.error("在MaterialImple类的doAddOneMaterial方法中利用BeanUtils类进行对象拷贝时出现了InvocationTargetException异常");
		}
		try {
			userActDAO.save(userActPO);
			addMessage = "add UserAct success!";

		} catch (Exception e) {
			addMessage = e.toString();
		}
		return addMessage;
	}

	// 删除一个userAct对象
	@Override
	public String doDeleteOneUserAct(Integer userId, Integer actId) {
		String okOrNot = null;
		UserAct userActPO = userActDAO.findByUserIdAndActId(userId, actId);
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

	// 更新一个userAct对象
	@Override
	public String doUpdateOneUserAct(UserActVO userActVO) {
		Integer actId = userActVO.getActId();
		Integer userId = userActVO.getUserId();
		UserAct userActPO = userActDAO.findByUserIdAndActId(userId, actId);
		String okOrNot = null;
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(userActPO, userActVO);

		} catch (IllegalAccessException e) {
			LOG.error("there is a IllegalAccessException while copy act in doUpdateOneact: " + e.toString());
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException while copy act in doUpdateOneact: " + e.toString());
		}
		try {
			userActDAO.merge(userActPO);
			okOrNot = "merge success!";
		} catch (Exception e) {
			okOrNot = e.toString();
		}
		
		return okOrNot;
	}

}
