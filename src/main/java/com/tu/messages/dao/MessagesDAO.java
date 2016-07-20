package com.tu.messages.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MessagesDAO extends HibernateDaoSupport implements
		MessagesDAOInterface {
	private static final Log LOG = LogFactory.getLog(MessagesDAO.class);
	public static final String Msg_ID = "msg_id";
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

	// 获取所有的messages
	@Override
	public List<Messages> findAll() {
		LOG.debug("finding all Message instances");
		try {
			String queryString = "from Messages";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOG.error("find all failed", re);
			throw re;
		}
	}

	// 通过messagesId来获取特定的messages对象
	@Override
	public Messages findById(java.lang.Integer msgId) {
		LOG.debug("getting Message instance with id: " + msgId);
		try {
			Messages instance = (Messages) getHibernateTemplate().get(
					"com.tu.messages.dao.Messages", msgId);
			return instance;
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	// 保存一个messages对象
	@Override
	public Integer save(Messages oneMessagePO) {
		LOG.debug("saving message instance");
		try {
			getHibernateTemplate().save(oneMessagePO);
			LOG.debug("save successful");
			return oneMessagePO.getMsgId();
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}
	}

}
