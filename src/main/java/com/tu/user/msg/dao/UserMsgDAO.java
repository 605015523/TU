package com.tu.user.msg.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserMsgDAO extends HibernateDaoSupport implements
		UserMsgDAOInterface {
	private static final Log LOGGER = LogFactory.getLog(UserMsgDAO.class);
	public static final String USER_ID = "user_id";
	public static final String MSG_ID = "group_id";
	public static final String READ_STATE = "read_state";

	@Override
	protected void initDao() {
		// do nothing
	}

	// 保存一个userMsg对象
	@Override
	public void save(UserMsg oneUserMsgPO) {
		LOGGER.debug("saving UserMsg instance");
		try {
			getHibernateTemplate().save(oneUserMsgPO);
			LOGGER.debug("save successful");
		} catch (RuntimeException re) {
			LOGGER.error("save failed", re);
			throw re;
		}
	}

	// 通过userId和msgId查找一个userMsg对象
	@Override
	public UserMsg findByUserIdAndMsgId(Integer userId, Integer msgId) {
		try {
			String hql = " from com.tu.user.msg.dao.UserMsg where user_id="
					+ userId.toString() + " and msg_id =" + msgId.toString();
			UserMsg userMsg = (UserMsg) this.getHibernateTemplate()
					.find(hql).get(0);

			return userMsg;
		} catch (RuntimeException re) {
			LOGGER.error("find by msgId and userId failed", re);
			throw re;
		}
	}

	// 通过userId查找所有该用户的messages消息
	@Override
	public List<UserMsg> findMsgByUserId(java.lang.Integer userId) {
		LOGGER.debug("find all UserMsg instance by userId");
		try {
			List<UserMsg> userMsg = getHibernateTemplate().find(
					"from com.tu.user.msg.dao.UserMsg where user_id = ?", userId);
			LOGGER.debug("find successful");
			return userMsg;
		} catch (RuntimeException re) {
			LOGGER.error("find msg by userId failed", re);
			throw re;
		}
	}

	// 删除一个userMsg对象
	@Override
	public void delete(UserMsg persistentInstance) {
		LOGGER.debug("deleting UserMsg instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOGGER.debug("delete successful");
		} catch (RuntimeException re) {
			LOGGER.error("delete failed", re);
			throw re;
		}
	}

	// 修改一个userMsg对象
	@Override
	public UserMsg merge(UserMsg detachedInstance) {
		LOGGER.debug("merging UserMsg instance");
		try {
			UserMsg result = (UserMsg) getHibernateTemplate().merge(
					detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

}
