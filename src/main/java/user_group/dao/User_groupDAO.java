package user_group.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class User_groupDAO extends HibernateDaoSupport implements
		User_groupDAOInterface {
	private static final Log log = LogFactory.getLog(User_groupDAO.class);
	public static final String USER_ID = "user_id";
	public static final String GROUP_ID = "group_id";

	protected void initDao() {
		// do nothing
	}

	// ����һ��User_group����
	public void save(User_group oneUser_groupPO) {
		log.debug("saving User_group instance");
		try {
			getHibernateTemplate().save(oneUser_groupPO);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	// ͨ��userId�ҵ�����user_group����
	public List findByUserId(Integer user_id) {
		try {

			return getHibernateTemplate()
					.find("from user_group.dao.User_group where user_id = ?",
							user_id);
		} catch (RuntimeException re) {
			log.error("find by userId failed", re);
			throw re;
		}
	}

	// ͨ��groupId�ҵ�����user_group����
	public List findByGroupId(Integer group_id) {
		try {

			return getHibernateTemplate().find(
					"from user_group.dao.User_group where group_id = ?",
					group_id);
		} catch (RuntimeException re) {
			log.error("find by groupId failed", re);
			throw re;
		}
	}

	// ͨ��groupId��userId�ҵ��ض�user_group����
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
			log.error("find by groupId and userId failed", re);
			throw re;
		}
	}

	// ɾ��ĳ��user_group����
	public void delete(User_group persistentInstance) {
		log.debug("deleting User_group instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public static User_groupDAO getFromApplicationContext(ApplicationContext AIM) {
		return (User_groupDAO) AIM.getBean("User_groupDAO");
	}

}
