package user_msg.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class User_msgDAO extends HibernateDaoSupport implements
		User_msgDAOInterface {
	private static final Log log = LogFactory.getLog(User_msgDAO.class);
	public static final String USER_ID = "user_id";
	public static final String MSG_ID = "group_id";
	public static final String READ_STATE = "read_state";

	protected void initDao() {
		// do nothing
	}

	// 保存一个user_msg对象
	public void save(User_msg oneUser_msgPO) {
		log.debug("saving User_msg instance");
		try {
			getHibernateTemplate().save(oneUser_msgPO);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	// 通过userId和msgId查找一个user_msg对象
	public User_msg findByUserIdAndMsgId(Integer user_id, Integer msg_id) {
		try {
			String hql = new String();
			hql = " from user_msg.dao.User_msg where user_id="
					+ user_id.toString() + " and msg_id =" + msg_id.toString();
			User_msg user_msg = (User_msg) this.getHibernateTemplate()
					.find(hql).get(0);

			return user_msg;
		} catch (RuntimeException re) {
			log.error("find by msgId and userId failed", re);
			throw re;
		}
	}

	// 通过userId查找所有该用户的messages消息
	public List findMsgByUserId(java.lang.Integer user_id) {
		log.debug("find all User_msg instance by userId");
		try {
			List user_msg = new ArrayList<User_msg>();
			user_msg = getHibernateTemplate().find(
					"from user_msg.dao.User_msg where user_id = ?", user_id);
			log.debug("find successful");
			return user_msg;
		} catch (RuntimeException re) {
			log.error("find msg by userId failed", re);
			throw re;
		}
	}

	// 删除一个user_msg对象
	public void delete(User_msg persistentInstance) {
		log.debug("deleting User_msg instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	// 修改一个user_msg对象
	public User_msg merge(User_msg detachedInstance) {
		log.debug("merging User_msg instance");
		try {
			User_msg result = (User_msg) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static User_msgDAO getFromApplicationContext(ApplicationContext AIM) {
		return (User_msgDAO) AIM.getBean("User_msgDAO");
	}

}
