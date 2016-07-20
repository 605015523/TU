package com.tu.userlogin.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserloginDAO extends HibernateDaoSupport implements
		UserloginDAOInterface {
	private static final Log LOG = LogFactory.getLog(UserloginDAO.class);
	// property constants
	private static final String USER_ID = "user_id";
	private static final String USER_NAME = "userName";
	private static final String USER_PASSWORD = "user_password";
	private static final String USER_ROLE = "user_role";
	private static final String USER_DEPT = "user_dept";
	private static final String IN_DATE = "in_date";
	private static final String OUT_DATE = "out_date";
	private static final String OLD_BALANCE = "old_balance";
	private static final String QUOTA = "quota";

	@Override
	protected void initDao() {
		// do nothing
	}

	// 保存一个user
	@Override
	public void save(Userlogin transientInstance) {
		LOG.debug("saving Userlogin instance");
		try {
			getHibernateTemplate().save(transientInstance);
			LOG.debug("save successful");
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}
	}

	// 删除一个用户
	@Override
	public void delete(Userlogin persistentInstance) {
		LOG.debug("deleting Userlogin instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOG.debug("delete successful");
		} catch (RuntimeException re) {
			LOG.error("delete failed", re);
			throw re;
		}
	}

	// 通过userId获取一个user对象
	@Override		
	public Userlogin findById(java.lang.Integer userId) {
		LOG.debug("getting Userlogin instance with id: " + userId);
		try {
			return (Userlogin) getHibernateTemplate().get(
					"com.tu.userlogin.dao.Userlogin", userId);
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<Userlogin> findByProperty(String propertyName, Object value) {
		LOG.debug("finding Userlogin instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Userlogin as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			LOG.error("find by property name failed", re);
			throw re;
		}
	}

	public Userlogin findByUserName(Object userName) {
		List<Userlogin> users = findByProperty(USER_NAME, userName);
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
	public List<Userlogin> findByUserRole(Object userRole) {
		return findByProperty(USER_ROLE, userRole);
	}

	public List<Userlogin> findByUserDept(Object userDept) {
		return findByProperty(USER_DEPT, userDept);
	}

	@Override
	public List<Userlogin> findAll() {
		LOG.debug("finding all Userlogin instances");
		try {
			String queryString = "from Userlogin";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOG.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Userlogin merge(Userlogin detachedInstance) {
		LOG.debug("merging Userlogin instance");
		try {
			Userlogin result = (Userlogin) getHibernateTemplate().merge(
					detachedInstance);
			LOG.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOG.error("merge failed", re);
			throw re;
		}
	}

}