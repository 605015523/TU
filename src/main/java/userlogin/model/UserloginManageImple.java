package userlogin.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import java.util.Observable;
import org.apache.commons.beanutils.BeanUtils;

import userlogin.dao.Userlogin;
import userlogin.dao.UserloginDAOInterface;

public class UserloginManageImple extends Observable implements
		UserloginManageInterface {
	private UserloginDAOInterface userloginDAO = null;

	public UserloginManageImple() {
		// 构造方法
	}

	public void setUserloginDAO(UserloginDAOInterface userloginDAO) {
		this.userloginDAO = userloginDAO;
	}

	// 获取所有用户的登录信息
	public ArrayList<UserloginVO> doGetAllUserlogin() {
		ArrayList<UserloginVO> knowledgeadministratorVOs = new ArrayList<UserloginVO>();
		ArrayList<Userlogin> knowledgeadministratorPOs = new ArrayList<Userlogin>();
		knowledgeadministratorPOs = (ArrayList) userloginDAO.findAll();
		for (int i = 0; i < knowledgeadministratorPOs.size(); i++) {
			Userlogin oneKnowledgeadministratorPO = new Userlogin();
			oneKnowledgeadministratorPO = knowledgeadministratorPOs.get(i);
			UserloginVO oneKnowledgeadministratorVO = new UserloginVO();
			try {
				BeanUtils.copyProperties(oneKnowledgeadministratorVO,
						oneKnowledgeadministratorPO);
				knowledgeadministratorVOs.add(oneKnowledgeadministratorVO);
			} catch (IllegalAccessException e) {
				System.out.println("出现了IllegalAccessException异常");
			} catch (InvocationTargetException e) {
				System.out.println("在出现了InvocationTargetException异常");
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
			BeanUtils.copyProperties(userInfoVO, userInfoPO);
		} catch (IllegalAccessException e) {
			System.out.println("出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			System.out.println("在出现了InvocationTargetException异常");
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
			System.out
					.println("there is a IllegalAccessException while copy act in doUpdateOneact.");
		} catch (InvocationTargetException e) {
			System.out
					.println("there is a InvocationTargetException while copy act in doUpdateOneact.");
		}
		try {
			userloginDAO.merge(oneUserPO);

		} catch (Exception e) {

		}

	}

}