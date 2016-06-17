package com.tu.user_act.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class User_actDAO extends HibernateDaoSupport implements
		User_actDAOInterface {
	private static final Log LOG = LogFactory.getLog(User_actDAO.class);
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
	public List findByUserId(java.lang.Integer user_id) {
		try {

			return getHibernateTemplate().find(
					"from com.tu.user_act.dao.User_act where user_id = ?", user_id);
		} catch (RuntimeException re) {
			LOG.error("find by userId failed", re);
			throw re;
		}
	}

	// 通过actId获取所有user_act对象，这样可以获取所有参与该活动的用户
	public List findByActId(java.lang.Integer act_id) {
		try {

			return getHibernateTemplate().find(
					"from com.tu.user_act.dao.User_act where act_id = ?", act_id);
		} catch (RuntimeException re) {
			LOG.error("find by userId failed", re);
			throw re;
		}
	}

	// 通过userId和actId可以获取到特定user_act对象
	public User_act findByUserIdAndActId(Integer user_id, Integer act_id) {
		try {
			String hql = new String();
			hql = " from com.tu.user_act.dao.User_act where user_id ="
					+ user_id.toString() + " and act_id=" + act_id.toString();
			User_act user_act = (User_act) this.getHibernateTemplate()
					.find(hql).get(0);

			return user_act;
		} catch (RuntimeException re) {
			LOG.error("find by actId and userId failed", re);
			throw re;
		}
	}

	// 删除某个user_act对象
	public void delete(User_act persistentInstance) {
		LOG.debug("deleting User_act instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOG.debug("delete successful");
		} catch (RuntimeException re) {
			LOG.error("delete failed", re);
			throw re;
		}
	}

	// 保存某个user_act对象
	public void save(User_act oneUser_actPO) {
		LOG.debug("saving User_act instance");
		try {
			getHibernateTemplate().save(oneUser_actPO);
			LOG.debug("save successful");
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}

	}

	// 更新某个user_act对象
	public void merge(User_act detachedInstance) {
		LOG.debug("merging User_act instance");
		try {
			User_act result = (User_act) getHibernateTemplate().merge(
					detachedInstance);
			LOG.debug("merge successful");
		} catch (RuntimeException re) {
			LOG.error("merge failed", re);
			throw re;
		}
	}

	public static User_actDAO getFromApplicationContext(ApplicationContext AIM) {
		return (User_actDAO) AIM.getBean("User_actDAO");
	}

}
