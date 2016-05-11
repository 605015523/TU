package user_act.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;

import user_act.dao.User_act;
import user_act.dao.User_actDAOInterface;

public class User_actImple extends Observable implements User_actInterface {
	private User_actDAOInterface user_actDAO = null;

	public User_actDAOInterface getUser_actDAO() {
		return user_actDAO;
	}

	public void setUser_actDAO(User_actDAOInterface userActDAO) {
		user_actDAO = userActDAO;
	}

	public User_actImple() {
	}

	// ͨ��userId��ActId����ȡ�ض�user_act����
	public User_actVO doGetOneActById(Integer userId, Integer actId) {
		User_actVO user_actVO = new User_actVO();
		User_act user_actPO = new User_act();
		user_actPO = user_actDAO.findByUserIdAndActId(userId, actId);
		try {
			BeanUtils.copyProperties(user_actVO, user_actPO);

		} catch (IllegalAccessException e) {
			System.out.println(" there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			System.out.println("there is a InvocationTargetException");
		}

		return user_actVO;
	}

	// ���һ��user_act����
	public String doAddOneUser_act(User_actVO oneUserActVO) {
		String addMessage = null;
		User_act user_actPO = new User_act();

		try { // ����Bean������ʵ�ּ򵥵ؿ���
			BeanUtils.copyProperties(user_actPO, oneUserActVO);
		} catch (IllegalAccessException e) {
			System.out
					.println("��MaterialImple���doAddOneMaterial����������BeanUtils����ж��󿽱�ʱ������IllegalAccessException�쳣");
		} catch (InvocationTargetException e) {
			System.out
					.println("��MaterialImple���doAddOneMaterial����������BeanUtils����ж��󿽱�ʱ������InvocationTargetException�쳣");
		}
		try {
			user_actDAO.save(user_actPO);
			addMessage = "add User_act success!";

		} catch (Exception e) {
			addMessage = e.toString();
		}
		return addMessage;
	}

	// ɾ��һ��user_act����
	public String doDeleteOneUser_act(Integer user_id, Integer act_id) {
		String OkOrNot = null;
		User_act user_actPO = new User_act();
		user_actPO = user_actDAO.findByUserIdAndActId(user_id, act_id);
		try {
			if (user_actDAO.findByUserIdAndActId(user_id, act_id) != null) {
				user_actDAO.delete(user_actPO);
				OkOrNot = "delete success!";
			} else {
				OkOrNot = "delete fail!";
			}

		} catch (Exception e) {
			OkOrNot = e.toString();
		}
		return OkOrNot;

	}

	// ����һ��user_act����
	public void doUpdateOneUser_act(User_actVO user_actVO) {
		User_act user_actPO = new User_act();
		Integer actId = user_actVO.getActId();
		Integer userId = user_actVO.getUserId();
		user_actPO = user_actDAO.findByUserIdAndActId(userId, actId);
		String OkOrNot = null;
		try { // ����Bean������ʵ�ּ򵥵ؿ���
			BeanUtils.copyProperties(user_actPO, user_actVO);

		} catch (IllegalAccessException e) {
			System.out
					.println("there is a IllegalAccessException while copy act in doUpdateOneact.");
		} catch (InvocationTargetException e) {
			System.out
					.println("there is a InvocationTargetException while copy act in doUpdateOneact.");
		}
		try {
			user_actDAO.merge(user_actPO);
			OkOrNot = "merge success!";
		} catch (Exception e) {
			OkOrNot = e.toString();
		}

	}

}
