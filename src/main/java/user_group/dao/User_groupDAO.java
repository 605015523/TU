package user_group.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class User_groupDAO extends HibernateDaoSupport implements
		User_groupDAOInterface {
	private static final Log LOG = LogFactory.getLog(User_groupDAO.class);
	public static final String USER_ID = "user_id";
	public static final String GROUP_ID = "group_id";

	protected void initDao() {
		// do nothing
	}

	// 保存一个User_group对象
	public void save(User_group oneUser_groupPO) {
		LOG.debug("saving User_group instance");
		try {
			getHibernateTemplate().save(oneUser_groupPO);
			LOG.debug("save successful");
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}
	}

	// 通过userId找到所有user_group对象
	public List findByUserId(Integer user_id) {
		try {

			return getHibernateTemplate()
					.find("from user_group.dao.User_group where user_id = ?",
							user_id);
		} catch (RuntimeException re) {
			LOG.error("find by userId failed", re);
			throw re;
		}
	}

	// 通过groupId找到所有user_group对象
	public List findByGroupId(Integer group_id) {
		try {

			return getHibernateTemplate().find(
					"from user_group.dao.User_group where group_id = ?",
					group_id);
		} catch (RuntimeException re) {
			LOG.error("find by groupId failed", re);
			throw re;
		}
	}

	// 通过groupId和userId找到特定user_group对象
	public User_group findByGroupIdAndUserId(Integer member_id, Integer group_id) {
		try {
			String hql = new String();
			hql = " from user_group.dao.User_group where user_id ="
					+ member_id.toString() + " and group_id="
					+ group_id.toString();
			User_group user_group = (User_group) this.getHibernateTemplate()
					.find(hql).get(0);

			return user_group;
		} catch (RuntimeException re) {
			LOG.error("find by groupId and userId failed", re);
			throw re;
		}
	}

	// 删除某个user_group对象
	public void delete(User_group persistentInstance) {
		LOG.debug("deleting User_group instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOG.debug("delete successful");
		} catch (RuntimeException re) {
			LOG.error("delete failed", re);
			throw re;
		}
	}

	public static User_groupDAO getFromApplicationContext(ApplicationContext AIM) {
		return (User_groupDAO) AIM.getBean("User_groupDAO");
	}

}
