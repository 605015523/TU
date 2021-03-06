package com.tu.model.userlogin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.userlogin.Userlogin;
import com.tu.dao.userlogin.UserloginDAOInterface;
import com.tu.mapper.DAOModelMapper;

public class UserloginManageImple extends Observable implements
		UserloginManageInterface {
	private static final Log LOG = LogFactory.getLog(UserloginManageImple.class);
	private UserloginDAOInterface userloginDAO = null;
	private DAOModelMapper daoModelMapper;

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
			userLoginsVOs.add(daoModelMapper.convertoToUserInfoVO(userLogin));
		}
		return userLoginsVOs;
	}

	// 通过userId获取该用户
	@Override
	public UserloginVO dogetOneUserInfoByUserId(Integer userId) {
		Userlogin userInfoPO = userloginDAO.findById(userId);
		
		return daoModelMapper.convertoToUserInfoVO(userInfoPO);
	}

	// 更新用户spending
	@Override
	public void doUpdateOneUserInfo(UserloginVO userVO) {
		Integer userId = userVO.getUserId();
		Userlogin userPO = userloginDAO.findById(userId);
		copyUserLoginVOtoPO(userVO, userPO);
		
		try {
			userloginDAO.merge(userPO);
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	@Override
	public void doCreateUser(UserloginVO userVO) {
		Userlogin userPO = new Userlogin();
		copyUserLoginVOtoPO(userVO, userPO);
		
		userloginDAO.save(userPO);
	}
	
	private void copyUserLoginVOtoPO(UserloginVO userVO, Userlogin userPO) {
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(userPO, userVO);
		} catch (IllegalAccessException e) {
			LOG.error("there is a IllegalAccessException while copy act in doUpdateOneact.");
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException while copy act in doUpdateOneact.");
		}
	}

	@Override
	public UserloginVO dogetOneUserInfoByUserName(String userName) {
		Userlogin userInfoPO = userloginDAO.findByUserName(userName);
		
		return daoModelMapper.convertoToUserInfoVO(userInfoPO);
	}

	public DAOModelMapper getDaoModelMapper() {
		return daoModelMapper;
	}

	public void setDaoModelMapper(DAOModelMapper daoModelMapper) {
		this.daoModelMapper = daoModelMapper;
	}
	
}