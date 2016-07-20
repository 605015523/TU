package com.tu.group.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GroupDAO extends HibernateDaoSupport implements GroupDAOInterface {

	private static final Log LOGGER = LogFactory.getLog(GroupDAO.class);
	// property constants
	public static final String GROUP_ID = "group_id";
	public static final String GROUP_NAME = "group_name";
	public static final String GROUP_LEADER_ID = "group_leader_id";
	public static final String DESCRIPTION = "description";

	@Override
	protected void initDao() {
		// do nothing
	}

	// 获取所有 group对象
	@Override
	public List<Group> findAll() {
		LOGGER.debug("finding all Group instances");
		try {
			String queryString = "from Group";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOGGER.error("find all failed", re);
			throw re;
		}
	}

	// 通过属性获取所有匹配的group对象
	@Override
	public List<Group> findByProperty(String propertyName, Object value) {
		LOGGER.debug("finding group instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Group as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			LOGGER.error("find by property name failed", re);
			throw re;
		}
	}

	// 通过groupId获取一个group对象
	@Override
	public Group findById(Integer groupId) {
		LOGGER.debug("getting group instance with id: " + groupId);
		try {
			return (Group) getHibernateTemplate().get(
					"group.dao.Group", groupId);
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	// 通过leaderId获取一个group对象
	@Override
	public Group findByUserId(Integer userId) {
		try {
			return (Group) getHibernateTemplate().find(
					"from com.tu.group.dao.Group where group_leader_id = ?", userId)
					.get(0);
		} catch (RuntimeException re) {
			LOGGER.error("find by group_leader_id failed", re);
			throw re;
		}
	}

	// 通过groupname获取group对象
	@Override
	public Group findByGroupName(String groupName) {
		try {
			return (Group) getHibernateTemplate().find(
					"from com.tu.group.dao.Group where group_name = ?", groupName)
					.get(0);
		} catch (RuntimeException re) {
			LOGGER.error("find by group_leader_id failed", re);
			throw re;
		}
	}

}
