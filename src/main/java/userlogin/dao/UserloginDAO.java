package userlogin.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserloginDAO extends HibernateDaoSupport implements
		UserloginDAOInterface {
	private static final Log log = LogFactory.getLog(UserloginDAO.class);
	// property constants
	public static final String USER_ID = "user_id";
	public static final String USER_NAME = "user_name";
	public static final String USER_PASSWORD = "user_password";
	public static final String USER_ROLE = "user_role";
	public static final String USER_DEPT = "user_dept";
	public static final String IN_DATE = "in_date";
	public static final String OUT_DATE = "out_date";
	public static final String OLD_BALANCE = "old_balance";
	public static final String QUOTA = "quota";

	protected void initDao() {
		// do nothing
	}

	// 保存一个user
	public void save(Userlogin transientInstance) {
		log.debug("saving Userlogin instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	// 删除一个用户
	public void delete(Userlogin persistentInstance) {
		log.debug("deleting Userlogin instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	// 通过userId获取一个user对象
	public Userlogin findById(java.lang.Integer user_id) {
		log.debug("getting Userlogin instance with id: " + user_id);
		try {
			Userlogin instance = (Userlogin) getHibernateTemplate().get(
					"userlogin.dao.Userlogin", user_id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Userlogin instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Userlogin as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByUserPassword(Object userPassword) {
		return findByProperty(USER_PASSWORD, userPassword);
	}

	public List findByUserRole(Object userRole) {
		return findByProperty(USER_ROLE, userRole);
	}

	public List findByUserDept(Object userDept) {
		return findByProperty(USER_DEPT, userDept);
	}

	public List findAll() {
		log.debug("finding all Userlogin instances");
		try {
			String queryString = "from Userlogin";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Userlogin merge(Userlogin detachedInstance) {
		log.debug("merging Userlogin instance");
		try {
			Userlogin result = (Userlogin) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static UserloginDAO getFromApplicationContext(ApplicationContext aim) {
		return (UserloginDAO) aim.getBean("UserloginDAO");
	}
}