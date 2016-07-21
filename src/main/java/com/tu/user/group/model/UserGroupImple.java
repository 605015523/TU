package com.tu.user.group.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.user.group.UserGroup;
import com.tu.dao.user.group.UserGroupDAOInterface;

public class UserGroupImple extends Observable implements UserGroupInterface {

	private static final Log LOGGER = LogFactory.getLog(UserGroupImple.class);
	private UserGroupDAOInterface userGroupDAO = null;

	public UserGroupDAOInterface getUserGroupDAO() {
		return userGroupDAO;
	}

	public void setUserGroupDAO(UserGroupDAOInterface userGroupDAO) {
		this.userGroupDAO = userGroupDAO;
	}

	public UserGroupImple() {
		// Do nothing
	}

	// 删除一个userGroup对象
	@Override
	public String doDeleteOneUserGroup(Integer memberId, Integer groupId) {
		String okOrNot = null;
		UserGroup userGroupPO = userGroupDAO
				.findByGroupIdAndUserId(memberId, groupId);

		try {
			if (userGroupPO != null) {
				userGroupDAO.delete(userGroupPO);
				okOrNot = "delete success!";
			} else {
				okOrNot = "delete fail!";
			}
		} catch (Exception e) {
			okOrNot = e.toString();
		}
		return okOrNot;

	}

	// 通过groupId获取某小组所有的成员的具体实现
	@Override
	public List<UserGroupVO> doGetAllMembersId(Integer groupId) {
		List<UserGroupVO> oneUserGroupVOs = new ArrayList<UserGroupVO>();
		List<UserGroup> oneUserGroupPOs = userGroupDAO.findByGroupId(groupId);
		
		for (int i = 0; i < oneUserGroupPOs.size(); i++) {
			UserGroup oneUserGroupPO = oneUserGroupPOs.get(i);
			UserGroupVO oneUserGroupVO = new UserGroupVO();
			try { // 利用Bean拷贝类实现简单地拷贝
				BeanUtils.copyProperties(oneUserGroupVO, oneUserGroupPO);
				oneUserGroupVOs.add(oneUserGroupVO);
			} catch (IllegalAccessException e) {
				LOGGER.error("在userviewImple类的doGetOneUserviewInfoByUserId方法中利用BeanUtils类进行对象拷贝时出现了IllegalAccessException异常");
			} catch (InvocationTargetException e) {
				LOGGER.error("在userviewImple类的doGetOneUserviewInfoByUserId方法中利用BeanUtils类进行对象拷贝时出现了InvocationTargetException异常");
			}

		}

		return oneUserGroupVOs;

	}
}
