package com.tu.group.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GroupDAO extends HibernateDaoSupport implements GroupDAOInterface {

	private static final Log LOG = LogFactory.getLog(GroupDAO.class);
	// property constants
	public static final String GROUP_ID = "group_id";
	public static final String GROUP_NAME = "group_name";
	public static final String GROUP_LEADER_ID = "group_leader_id";
	public static final String DESCRIPTION = "description";

	protected void initDao() {
		// do nothing
	}

	// 获取所有 group对象
	public List<Group> findAll() {
		LOG.debug("finding all Group instances");
		try {
			String queryString = "from Group";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOG.error("find all failed", re);
			throw re;
		}
	}

	// 通过属性获取所有匹配的group对象
	public List<Group> findByProperty(String propertyName, Object value) {
		LOG.debug("finding group instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Group as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			LOG.error("find by property name failed", re);
			throw re;
		}
	}

	// 通过groupId获取一个group对象
	public Group findById(Integer groupId) {
		LOG.debug("getting group instance with id: " + groupId);
		try {
			Group instance = (Group) getHibernateTemplate().get(
					"group.dao.Group", groupId);
			return instance;
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	// 通过leaderId获取一个group对象
	public Group findByUserId(Integer user_id) {
		try {
			Group group = (Group) getHibernateTemplate().find(
					"from com.tu.group.dao.Group where group_leader_id = ?", user_id)
					.get(0);

			return group;
		} catch (RuntimeException re) {
			LOG.error("find by group_leader_id failed", re);
			throw re;
		}
	}

	// 通过groupname获取group对象
	public Group findByGroupName(String groupName) {
		try {
			Group group = (Group) getHibernateTemplate().find(
					"from com.tu.group.dao.Group where group_name = ?", groupName)
					.get(0);
			return group;
		} catch (RuntimeException re) {
			LOG.error("find by group_leader_id failed", re);
			throw re;
		}
	}

	public static GroupDAO getFromApplicationContext(ApplicationContext AIM) {
		return (GroupDAO) AIM.getBean("GroupDAO");
	}

}
