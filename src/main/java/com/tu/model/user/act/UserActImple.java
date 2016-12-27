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
		
		copyPropertiesVOtoPO(oneUserActVO, userActPO);

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
		String result = null;
		UserAct userActPO = userActDAO.findByUserIdAndActId(userId, actId);
		try {
			if (userActPO != null) {
				userActDAO.delete(userActPO);
				result = "delete success!";
			} else {
				result = "delete fail!";
			}

		} catch (Exception e) {
			result = e.toString();
		}
		return result;

	}

	// 更新一个userAct对象
	@Override
	public String doUpdateOneUserAct(UserActVO userActVO) {
		Integer actId = userActVO.getActId();
		Integer userId = userActVO.getUser().getUserId();
		UserAct userActPO = userActDAO.findByUserIdAndActId(userId, actId);
		String result = null;
		copyPropertiesVOtoPO(userActVO, userActPO);
		
		try {
			userActDAO.merge(userActPO);
			result = "merge success!";
		} catch (Exception e) {
			result = e.toString();
		}
		
		return result;
	}
	
	private void copyPropertiesVOtoPO(UserActVO userActVO, UserAct userActPO) {
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(userActPO, userActVO);
			userActPO.setUserId(userActVO.getUser().getUserId());
		} catch (IllegalAccessException e) {
			LOG.error("there is a IllegalAccessException while copy act in doUpdateOneact: ", e);
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException while copy act in doUpdateOneact: ", e);
		}
	}

}
