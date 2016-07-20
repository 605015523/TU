package com.tu.group.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.group.dao.Group;
import com.tu.group.dao.GroupDAOInterface;

public class GroupImple extends Observable implements GroupInterface {

	private static final Log LOG = LogFactory.getLog(GroupImple.class);
	private GroupDAOInterface groupDAO = null;

	public GroupImple() {

	}

	public GroupDAOInterface getGroupDAO() {
		return this.groupDAO;
	}

	public void setGroupDAO(GroupDAOInterface groupDAO) {
		this.groupDAO = groupDAO;
	}

	@Override
	public GroupVO dogetOneGroup(Integer userId) {
		GroupVO groupVO = new GroupVO();
		Group groupPO = groupDAO.findByUserId(userId);
		try {
			BeanUtils.copyProperties(groupVO, groupPO);

		} catch (IllegalAccessException e) {
			LOG.error("there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException");
		}

		return groupVO;
	}

	@Override
	public GroupVO dogetOneGroupByName(String groupName) {
		GroupVO groupVO = new GroupVO();
		Group groupPO = groupDAO.findByGroupName(groupName);
		try {
			BeanUtils.copyProperties(groupVO, groupPO);

		} catch (IllegalAccessException e) {
			LOG.error(" there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException");
		}

		return groupVO;
	}

	@Override
	public List<GroupVO> dogetAllGroup() {
		List<GroupVO> allGroupVO = new ArrayList<GroupVO>();
		List<Group> allGroupPO = groupDAO.findAll();
		for (int i = 0; i < allGroupPO.size(); i++) {
			GroupVO oneGroupVO = new GroupVO();
			Group oneGroupPO = allGroupPO.get(i);
			try {
				BeanUtils.copyProperties(oneGroupVO, oneGroupPO);
				allGroupVO.add(oneGroupVO);

			} catch (IllegalAccessException e) {
				LOG.error(" there is a IllegalAccessException");
			} catch (InvocationTargetException e) {
				LOG.error("there is a InvocationTargetException");
			}
		}

		return allGroupVO;
	}

}
