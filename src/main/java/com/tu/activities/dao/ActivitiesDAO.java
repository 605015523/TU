package com.tu.activities.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ActivitiesDAO extends HibernateDaoSupport implements
		ActivitiesDAOInterface {

	private static final Log LOG = LogFactory.getLog(ActivitiesDAO.class);
	public static final String ACT_ID = "act_id";
	public static final String GROUP_ID = "group_id";
	public static final String ACT_NAME = "act_name";
	public static final String ACT_MONEY = "act_money";
	public static final String DESCRIPTION = "description";
	public static final String ENROLL_START_DATE = "enroll_start_date";
	public static final String ENROLL_END_DATE = "enroll_end_date";
	public static final String ACT_DATE = "act_date";
	public static final String STATE = "state";

	protected void initDao() {
		// do nothing
	}

	public List<Activities> findAll() {
		LOG.debug("finding all activities instances");
		try {
			String queryString = "from Activities";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOG.error("find all failed", re);
			throw re;
		}
	}

	public List<Activities> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	// 通过actId找到并返回这个Activities对象
	public Activities findById(java.lang.Integer act_id) {
		LOG.debug("getting Activities instance with id: " + act_id);
		try {
			Activities instance = (Activities) getHibernateTemplate().get(
					"com.tu.activities.dao.Activities", act_id);
			return instance;
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	// 通过groupId找到并返回所有属于这个group的对象
	public List findByGroupId(Integer group_id) {
		try {
			return getHibernateTemplate().find(
					"from com.tu.activities.dao.Activities where group_id = ?",
					group_id);
		} catch (RuntimeException re) {
			LOG.error("find by userId failed", re);
			throw re;
		}
	}

	// 保存一个Activities对象
	public Integer save(Activities oneActivitiesPO) {
		LOG.debug("saving Activities instance");
		try {
			getHibernateTemplate().save(oneActivitiesPO);
			LOG.debug("save successful");
			return oneActivitiesPO.getActId();
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}

	}

	// 更新一个Activities
	public Activities merge(Activities detachedInstance) {
		LOG.debug("merging activitiy instance");
		try {
			Activities result = (Activities) getHibernateTemplate().merge(
					detachedInstance);
			LOG.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOG.error("merge failed", re);
			throw re;
		}
	}

	// 删除一个Activities对象
	public void delete(Activities oneActivitiesPO) {
		// TODO Auto-generated method stub

	}

	public static ActivitiesDAO getFromApplicationContext(ApplicationContext aim) {
		return (ActivitiesDAO) aim.getBean("ActivitiesDAO");
	}
}
