package com.tu.model.userlogin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.userlogin.Userlogin;
import com.tu.dao.userlogin.UserloginDAOInterface;

public class UserloginManageImple extends Observable implements
		UserloginManageInterface {
	private static final Log LOG = LogFactory.getLog(UserloginManageImple.class);
	private UserloginDAOInterface userloginDAO = null;

	public UserloginManageImple() {
		// 构造方法
	}

	public void setUserloginDAO(UserloginDAOInterface userloginDAO) {
		this.userloginDAO = userloginDAO;
	}

	// 获取所有用户的登录信息
	@Override
	public List<UserloginVO> doGetAllUserlogin() {
		List<UserloginVO> userLoginsVOs = new ArrayList<UserloginVO>();
		List<Userlogin> userLogins = userloginDAO.findAll();
		for (Userlogin userLogin : userLogins) {
			userLoginsVOs.add(convertoToUserInfoVO(userLogin));
		}
		return userLoginsVOs;
	}

	// 通过userId获取该用户
	@Override
	public UserloginVO dogetOneUserInfoByUserId(Integer userId) {
		Userlogin userInfoPO = userloginDAO.findById(userId);
		
		return convertoToUserInfoVO(userInfoPO);
	}

	// 更新用户spending
	@Override
	public void doUpdateOneUserInfo(UserloginVO oneUserVO) {
		Integer userId = oneUserVO.getUserId();
		Userlogin oneUserPO = userloginDAO.findById(userId);

		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneUserPO, oneUserVO);
		} catch (IllegalAccessException e) {
			LOG.error("there is a IllegalAccessException while copy act in doUpdateOneact.");
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException while copy act in doUpdateOneact.");
		}
		try {
			userloginDAO.merge(oneUserPO);
		} catch (Exception e) {
			LOG.error(e);
		}

	}

	@Override
	public UserloginVO dogetOneUserInfoByUserName(String userName) {
		Userlogin userInfoPO = userloginDAO.findByUserName(userName);
		
		return convertoToUserInfoVO(userInfoPO);
	}
	
	private UserloginVO convertoToUserInfoVO(Userlogin userInfoPO) {
		UserloginVO userInfoVO = new UserloginVO();
		try {
			PropertyUtils.copyProperties(userInfoVO, userInfoPO);
		} catch (IllegalAccessException e) {
			LOG.error("出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			LOG.error("在出现了InvocationTargetException异常");
		} catch (NoSuchMethodException e) {
			LOG.error("Method not found" + e.getMessage());
		}
		return userInfoVO;
	}

}