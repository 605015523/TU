package com.tu.user.act.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class User_actDAO extends HibernateDaoSupport implements
		User_actDAOInterface {
	private static final Log LOGGER = LogFactory.getLog(User_actDAO.class);
	public static final String USER_ID = "user_id";
	public static final String ACT_ID = "act_id";
	public static final String PARTICIPATER_NO = "participater_number";
	public static final String CONSUMPTION = "consumption";
	public static final String STATE = "state";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	// 通过用户Id获取所有user_act对象，这样可以获取所有用户参与的活动
	public List<User_act> findByUserId(java.lang.Integer userId) {
		try {

			return getHibernateTemplate().find(
					"from com.tu.user.act.dao.User_act where user_id = ?", userId);
		} catch (RuntimeException re) {
			LOGGER.error("find by userId failed", re);
			throw re;
		}
	}

	// 通过actId获取所有user_act对象，这样可以获取所有参与该活动的用户
	public List<User_act> findByActId(java.lang.Integer actId) {
		try {

			return getHibernateTemplate().find(
					"from com.tu.user.act.dao.User_act where act_id = ?", actId);
		} catch (RuntimeException re) {
			LOGGER.error("find by userId failed", re);
			throw re;
		}
	}

	// 通过userId和actId可以获取到特定user_act对象
	public User_act findByUserIdAndActId(Integer userId, Integer actId) {
		try {
			String hql = " from com.tu.user.act.dao.User_act where user_id ="
					+ userId.toString() + " and act_id=" + actId.toString();
			User_act userAct = (User_act) this.getHibernateTemplate()
					.find(hql).get(0);

			return userAct;
		} catch (RuntimeException re) {
			LOGGER.error("find by actId and userId failed", re);
			throw re;
		}
	}

	// 删除某个user_act对象
	public void delete(User_act persistentInstance) {
		LOGGER.debug("deleting User_act instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOGGER.debug("delete successful");
		} catch (RuntimeException re) {
			LOGGER.error("delete failed", re);
			throw re;
		}
	}

	// 保存某个user_act对象
	public void save(User_act oneUserActPO) {
		LOGGER.debug("saving User_act instance");
		try {
			getHibernateTemplate().save(oneUserActPO);
			LOGGER.debug("save successful");
		} catch (RuntimeException re) {
			LOGGER.error("save failed", re);
			throw re;
		}

	}

	// 更新某个user_act对象
	public void merge(User_act detachedInstance) {
		LOGGER.debug("merging User_act instance");
		try {
			User_act result = (User_act) getHibernateTemplate().merge(
					detachedInstance);
			LOGGER.debug("merge successful");
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

}
