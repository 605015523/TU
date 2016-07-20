package com.tu.user.group.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserGroupDAO extends HibernateDaoSupport implements
		UserGroupDAOInterface {
	private static final Log LOGGER = LogFactory.getLog(UserGroupDAO.class);
	public static final String USER_ID = "user_id";
	public static final String GROUP_ID = "group_id";

	@Override
	protected void initDao() {
		// do nothing
	}

	// 保存一个UserGroup对象
	@Override
	public void save(UserGroup oneUserGroupPO) {
		LOGGER.debug("saving UserGroup instance");
		try {
			getHibernateTemplate().save(oneUserGroupPO);
			LOGGER.debug("save successful");
		} catch (RuntimeException re) {
			LOGGER.error("save failed", re);
			throw re;
		}
	}

	// 通过userId找到所有userGroup对象
	@Override
	public List<UserGroup> findByUserId(Integer userId) {
		try {

			return getHibernateTemplate()
					.find("from com.tu.user.group.dao.UserGroup where user_id = ?",
							userId);
		} catch (RuntimeException re) {
			LOGGER.error("find by userId failed", re);
			throw re;
		}
	}

	// 通过groupId找到所有userGroup对象
	@Override
	public List<UserGroup> findByGroupId(Integer groupId) {
		try {

			return getHibernateTemplate().find(
					"from com.tu.user.group.dao.UserGroup where group_id = ?",
					groupId);
		} catch (RuntimeException re) {
			LOGGER.error("find by groupId failed", re);
			throw re;
		}
	}

	// 通过groupId和userId找到特定userGroup对象
	@Override
	public UserGroup findByGroupIdAndUserId(Integer memberId, Integer groupId) {
		try {
			String hql = " from com.tu.user.group.dao.UserGroup where user_id ="
					+ memberId.toString() + " and group_id="
					+ groupId.toString();
			UserGroup userGroup = (UserGroup) this.getHibernateTemplate()
					.find(hql).get(0);

			return userGroup;
		} catch (RuntimeException re) {
			LOGGER.error("find by groupId and userId failed", re);
			throw re;
		}
	}

	// 删除某个userGroup对象
	@Override
	public void delete(UserGroup persistentInstance) {
		LOGGER.debug("deleting UserGroup instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOGGER.debug("delete successful");
		} catch (RuntimeException re) {
			LOGGER.error("delete failed", re);
			throw re;
		}
	}

}
