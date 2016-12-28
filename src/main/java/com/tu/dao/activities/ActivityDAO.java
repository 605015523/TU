package com.tu.dao.activities;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tu.model.activities.ActivitiesConstant;

public class ActivityDAO extends HibernateDaoSupport implements
		ActivityDAOInterface {

	private static final Log LOG = LogFactory.getLog(ActivityDAO.class);
	public static final String ACT_ID = "act_id";
	public static final String GROUP_ID = "group_id";
	public static final String ACT_NAME = "act_name";
	public static final String ACT_MONEY = "act_money";
	public static final String DESCRIPTION = "description";
	public static final String ENROLL_START_DATE = "enroll_start_date";
	public static final String ENROLL_END_DATE = "enroll_end_date";
	public static final String ACT_DATE = "act_date";
	public static final String STATE = "state";

	@Override
	protected void initDao() {
		// do nothing
	}

	@Override
	public List<Activity> findAll() {
		LOG.debug("finding all activities instances");
		try {
			String queryString = "from Activity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOG.error("find all failed", re);
			throw re;
		}
	}
	
	@Override
	public List<Activity> findUpcoming() {
		LOG.debug("finding all activities instances");
		try {
			String queryString = "from Activity where state = ? and act_date >= current_date()";
			return getHibernateTemplate().find(queryString, ActivitiesConstant.STATE_PUBLISH);
		} catch (RuntimeException re) {
			LOG.error("find all failed", re);
			throw re;
		}
	}

	public List<Activity> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	// Get Activity by its ID
	@Override
	public Activity findById(Integer actId) {
		LOG.debug("getting Activities instance with id: " + actId);
		try {
			return (Activity) getHibernateTemplate().get(
					"com.tu.dao.activities.Activity", actId);
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	// 通过groupId找到并返回所有属于这个group的对象
	@Override
	public List<Activity> findByGroupId(Integer groupId) {
		try {
			return getHibernateTemplate().find(
					"from com.tu.dao.activities.Activity where group_id = ?",
					groupId);
		} catch (RuntimeException re) {
			LOG.error("find by userId failed", re);
			throw re;
		}
	}

	// 保存一个Activities对象
	@Override
	public Integer save(Activity oneActivityPO) {
		LOG.debug("saving Activities instance");
		try {
			getHibernateTemplate().save(oneActivityPO);
			LOG.debug("save successful");
			return oneActivityPO.getActId();
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}

	}

	// 更新一个Activities
	@Override
	public Activity merge(Activity detachedInstance) {
		LOG.debug("merging activitiy instance");
		try {
			Activity result = getHibernateTemplate().merge(
					detachedInstance);
			LOG.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOG.error("merge failed", re);
			throw re;
		}
	}

	// Delete the given Activity object
	@Override
	public void delete(Activity oneActivityPO) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Activity> findByYear(Integer year) {
		try {
			return getHibernateTemplate().find(
					"from com.tu.dao.activities.Activity where year(act_date) = ?",
					year);
		} catch (RuntimeException re) {
			LOG.error("find by userId failed", re);
			throw re;
		}
	}
}
