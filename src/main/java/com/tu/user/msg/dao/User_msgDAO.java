package com.tu.user.msg.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class User_msgDAO extends HibernateDaoSupport implements
		User_msgDAOInterface {
	private static final Log LOGGER = LogFactory.getLog(User_msgDAO.class);
	public static final String USER_ID = "user_id";
	public static final String MSG_ID = "group_id";
	public static final String READ_STATE = "read_state";

	protected void initDao() {
		// do nothing
	}

	// 保存一个user_msg对象
	public void save(User_msg oneUserMsgPO) {
		LOGGER.debug("saving User_msg instance");
		try {
			getHibernateTemplate().save(oneUserMsgPO);
			LOGGER.debug("save successful");
		} catch (RuntimeException re) {
			LOGGER.error("save failed", re);
			throw re;
		}
	}

	// 通过userId和msgId查找一个user_msg对象
	public User_msg findByUserIdAndMsgId(Integer userId, Integer msgId) {
		try {
			String hql = " from com.tu.user.msg.dao.User_msg where user_id="
					+ userId.toString() + " and msg_id =" + msgId.toString();
			User_msg userMsg = (User_msg) this.getHibernateTemplate()
					.find(hql).get(0);

			return userMsg;
		} catch (RuntimeException re) {
			LOGGER.error("find by msgId and userId failed", re);
			throw re;
		}
	}

	// 通过userId查找所有该用户的messages消息
	public List<User_msg> findMsgByUserId(java.lang.Integer userId) {
		LOGGER.debug("find all User_msg instance by userId");
		try {
			List<User_msg> userMsg = getHibernateTemplate().find(
					"from com.tu.user.msg.dao.User_msg where user_id = ?", userId);
			LOGGER.debug("find successful");
			return userMsg;
		} catch (RuntimeException re) {
			LOGGER.error("find msg by userId failed", re);
			throw re;
		}
	}

	// 删除一个user_msg对象
	public void delete(User_msg persistentInstance) {
		LOGGER.debug("deleting User_msg instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOGGER.debug("delete successful");
		} catch (RuntimeException re) {
			LOGGER.error("delete failed", re);
			throw re;
		}
	}

	// 修改一个user_msg对象
	public User_msg merge(User_msg detachedInstance) {
		LOGGER.debug("merging User_msg instance");
		try {
			User_msg result = (User_msg) getHibernateTemplate().merge(
					detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

}
