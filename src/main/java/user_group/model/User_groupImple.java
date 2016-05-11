package user_group.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import org.apache.commons.beanutils.BeanUtils;

import user_group.dao.User_group;
import user_group.dao.User_groupDAOInterface;

public class User_groupImple extends Observable implements User_groupInterface {

	private User_groupDAOInterface user_groupDAO = null;

	public User_groupDAOInterface getUser_groupDAO() {
		return user_groupDAO;
	}

	public void setUser_groupDAO(User_groupDAOInterface userGroupDAO) {
		user_groupDAO = userGroupDAO;
	}

	public User_groupImple() {

	}

	// 删除一个user_group对象
	public String doDeleteOneUser_group(Integer member_id, Integer group_id) {
		String OkOrNot = null;
		User_group user_groupPO = new User_group();
		user_groupPO = user_groupDAO
				.findByGroupIdAndUserId(member_id, group_id);

		try {

			if (user_groupDAO.findByGroupIdAndUserId(member_id, group_id) != null) {
				user_groupDAO.delete(user_groupPO);
				OkOrNot = "delete success!";
			} else {
				OkOrNot = "delete fail!";
			}
		} catch (Exception e) {
			OkOrNot = e.toString();
		}
		return OkOrNot;

	}

	// 通过groupId获取某小组所有的成员的具体实现
	public List<User_groupVO> doGetAllMembersId(Integer groupId) {
		List<User_group> oneUser_groupPOs = new ArrayList<User_group>();
		List<User_groupVO> oneUser_groupVOs = new ArrayList<User_groupVO>();

		oneUser_groupPOs = user_groupDAO.findByGroupId(groupId);
		for (int i = 0; i < oneUser_groupPOs.size(); i++) {
			User_group oneUser_groupPO = oneUser_groupPOs.get(i);
			User_groupVO oneUser_groupVO = new User_groupVO();
			try { // 利用Bean拷贝类实现简单地拷贝
				BeanUtils.copyProperties(oneUser_groupVO, oneUser_groupPO);
				oneUser_groupVOs.add(oneUser_groupVO);
			} catch (IllegalAccessException e) {
				System.out
						.println("在userviewImple类的doGetOneUserviewInfoByUserId方法中利用BeanUtils类进行对象拷贝时出现了IllegalAccessException异常");
			} catch (InvocationTargetException e) {
				System.out
						.println("在userviewImple类的doGetOneUserviewInfoByUserId方法中利用BeanUtils类进行对象拷贝时出现了InvocationTargetException异常");
			}

		}

		return oneUser_groupVOs;

	}
}
