package com.tu.user_msg.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class User_msgDAO extends HibernateDaoSupport implements
		User_msgDAOInterface {
	private static final Log LOG = LogFactory.getLog(User_msgDAO.class);
	public static final String USER_ID = "user_id";
	public static final String MSG_ID = "group_id";
	public static final String READ_STATE = "read_state";

	protected void initDao() {
		// do nothing
	}

	// 保存一个user_msg对象
	public void save(User_msg oneUser_msgPO) {
		LOG.debug("saving User_msg instance");
		try {
			getHibernateTemplate().save(oneUser_msgPO);
			LOG.debug("save successful");
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}
	}

	// 通过userId和msgId查找一个user_msg对象
	public User_msg findByUserIdAndMsgId(Integer user_id, Integer msg_id) {
		try {
			String hql = new String();
			hql = " from com.tu.user_msg.dao.User_msg where user_id="
					+ user_id.toString() + " and msg_id =" + msg_id.toString();
			User_msg user_msg = (User_msg) this.getHibernateTemplate()
					.find(hql).get(0);

			return user_msg;
		} catch (RuntimeException re) {
			LOG.error("find by msgId and userId failed", re);
			throw re;
		}
	}

	// 通过userId查找所有该用户的messages消息
	public List<User_msg> findMsgByUserId(java.lang.Integer user_id) {
		LOG.debug("find all User_msg instance by userId");
		try {
			List<User_msg> user_msg = new ArrayList<User_msg>();
			user_msg = getHibernateTemplate().find(
					"from com.tu.user_msg.dao.User_msg where user_id = ?", user_id);
			LOG.debug("find successful");
			return user_msg;
		} catch (RuntimeException re) {
			LOG.error("find msg by userId failed", re);
			throw re;
		}
	}

	// 删除一个user_msg对象
	public void delete(User_msg persistentInstance) {
		LOG.debug("deleting User_msg instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOG.debug("delete successful");
		} catch (RuntimeException re) {
			LOG.error("delete failed", re);
			throw re;
		}
	}

	// 修改一个user_msg对象
	public User_msg merge(User_msg detachedInstance) {
		LOG.debug("merging User_msg instance");
		try {
			User_msg result = (User_msg) getHibernateTemplate().merge(
					detachedInstance);
			LOG.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOG.error("merge failed", re);
			throw re;
		}
	}

	public static User_msgDAO getFromApplicationContext(ApplicationContext AIM) {
		return (User_msgDAO) AIM.getBean("User_msgDAO");
	}

}
