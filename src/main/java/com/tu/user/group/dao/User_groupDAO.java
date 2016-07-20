package com.tu.user.group.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class User_groupDAO extends HibernateDaoSupport implements
		User_groupDAOInterface {
	private static final Log LOGGER = LogFactory.getLog(User_groupDAO.class);
	public static final String USER_ID = "user_id";
	public static final String GROUP_ID = "group_id";

	protected void initDao() {
		// do nothing
	}

	// 保存一个User_group对象
	public void save(User_group oneUserGroupPO) {
		LOGGER.debug("saving User_group instance");
		try {
			getHibernateTemplate().save(oneUserGroupPO);
			LOGGER.debug("save successful");
		} catch (RuntimeException re) {
			LOGGER.error("save failed", re);
			throw re;
		}
	}

	// 通过userId找到所有user_group对象
	public List<User_group> findByUserId(Integer userId) {
		try {

			return getHibernateTemplate()
					.find("from com.tu.user.group.dao.User_group where user_id = ?",
							userId);
		} catch (RuntimeException re) {
			LOGGER.error("find by userId failed", re);
			throw re;
		}
	}

	// 通过groupId找到所有user_group对象
	public List<User_group> findByGroupId(Integer groupId) {
		try {

			return getHibernateTemplate().find(
					"from com.tu.user.group.dao.User_group where group_id = ?",
					groupId);
		} catch (RuntimeException re) {
			LOGGER.error("find by groupId failed", re);
			throw re;
		}
	}

	// 通过groupId和userId找到特定user_group对象
	public User_group findByGroupIdAndUserId(Integer memberId, Integer groupId) {
		try {
			String hql = " from com.tu.user.group.dao.User_group where user_id ="
					+ memberId.toString() + " and group_id="
					+ groupId.toString();
			User_group userGroup = (User_group) this.getHibernateTemplate()
					.find(hql).get(0);

			return userGroup;
		} catch (RuntimeException re) {
			LOGGER.error("find by groupId and userId failed", re);
			throw re;
		}
	}

	// 删除某个user_group对象
	public void delete(User_group persistentInstance) {
		LOGGER.debug("deleting User_group instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOGGER.debug("delete successful");
		} catch (RuntimeException re) {
			LOGGER.error("delete failed", re);
			throw re;
		}
	}

}
