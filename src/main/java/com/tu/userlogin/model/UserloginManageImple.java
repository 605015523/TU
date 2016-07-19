package com.tu.userlogin.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.userlogin.dao.Userlogin;
import com.tu.userlogin.dao.UserloginDAOInterface;

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
	public List<UserloginVO> doGetAllUserlogin() {
		List<UserloginVO> knowledgeadministratorVOs = new ArrayList<UserloginVO>();
		List<Userlogin> knowledgeadministratorPOs = userloginDAO.findAll();
		for (int i = 0; i < knowledgeadministratorPOs.size(); i++) {
			Userlogin oneKnowledgeadministratorPO = knowledgeadministratorPOs.get(i);
			UserloginVO oneKnowledgeadministratorVO = new UserloginVO();
			try {
				PropertyUtils.copyProperties(oneKnowledgeadministratorVO,
						oneKnowledgeadministratorPO);
				knowledgeadministratorVOs.add(oneKnowledgeadministratorVO);
			} catch (IllegalAccessException e) {
				LOG.error("出现了IllegalAccessException异常");
			} catch (InvocationTargetException e) {
				LOG.error("在出现了InvocationTargetException异常");
			} catch (NoSuchMethodException e) {
				LOG.error("Method not found" + e.getMessage());
			}
		}
		return knowledgeadministratorVOs;
	}

	// 通过userId获取该用户
	public UserloginVO dogetOneUserInfoByUserId(Integer userId) {
		Userlogin userInfoPO = new Userlogin();
		userInfoPO = userloginDAO.findById(userId);
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

	// 更新用户spending
	public void doUpdateOneUserInfo(UserloginVO oneUserVO) {
		Userlogin oneUserPO = new Userlogin();
		Integer userId = oneUserVO.getUserId();
		oneUserPO = userloginDAO.findById(userId);

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

		}

	}

}