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
		// ���췽��
	}

	public void setUserloginDAO(UserloginDAOInterface userloginDAO) {
		this.userloginDAO = userloginDAO;
	}

	// ��ȡ�����û��ĵ�¼��Ϣ
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
				System.out.println("������IllegalAccessException�쳣");
			} catch (InvocationTargetException e) {
				System.out.println("�ڳ�����InvocationTargetException�쳣");
			}
		}
		return knowledgeadministratorVOs;
	}

	// ͨ��userId��ȡ���û�
	public UserloginVO dogetOneUserInfoByUserId(Integer userId) {
		Userlogin userInfoPO = new Userlogin();
		userInfoPO = userloginDAO.findById(userId);
		UserloginVO userInfoVO = new UserloginVO();
		try {
			BeanUtils.copyProperties(userInfoVO, userInfoPO);
		} catch (IllegalAccessException e) {
			System.out.println("������IllegalAccessException�쳣");
		} catch (InvocationTargetException e) {
			System.out.println("�ڳ�����InvocationTargetException�쳣");
		}
		return userInfoVO;

	}

	// �����û�spending
	public void doUpdateOneUserInfo(UserloginVO oneUserVO) {
		Userlogin oneUserPO = new Userlogin();
		Integer userId = oneUserVO.getUserId();
		oneUserPO = userloginDAO.findById(userId);

		try { // ����Bean������ʵ�ּ򵥵ؿ���
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