package com.tu.dao.user.act;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserActDAO extends HibernateDaoSupport implements
		UserActDAOInterface {
	private static final Log LOGGER = LogFactory.getLog(UserActDAO.class);
	public static final String USER_ID = "user_id";
	public static final String ACT_ID = "act_id";
	public static final String PARTICIPATER_NO = "participater_number";
	public static final String CONSUMPTION = "consumption";
	public static final String STATE = "state";
	public static final String REMARK = "remark";

	@Override
	protected void initDao() {
		// do nothing
	}

	// 通过用户Id获取所有userAct对象，这样可以获取所有用户参与的活动
	@Override
	public List<UserAct> findByUserId(java.lang.Integer userId) {
		try {

			return getHibernateTemplate().find(
					"from com.tu.dao.user.act.UserAct where user_id = ?", userId);
		} catch (RuntimeException re) {
			LOGGER.error("find by userId failed", re);
			throw re;
		}
	}

	// 通过actId获取所有userAct对象，这样可以获取所有参与该活动的用户
	@Override
	public List<UserAct> findByActId(java.lang.Integer actId) {
		try {

			return getHibernateTemplate().find(
					"from com.tu.dao.user.act.UserAct where act_id = ?", actId);
		} catch (RuntimeException re) {
			LOGGER.error("find by userId failed", re);
			throw re;
		}
	}

	// 通过userId和actId可以获取到特定userAct对象
	@Override
	public UserAct findByUserIdAndActId(Integer userId, Integer actId) {
		UserAct userAct = null;
		
		try {
			String hql = " from com.tu.dao.user.act.UserAct where user_id ="
					+ userId.toString() + " and act_id=" + actId.toString();
			
			List<UserAct> userActs = this.getHibernateTemplate().find(hql);
			if (CollectionUtils.isNotEmpty(userActs)) {
				userAct = userActs.get(0);
			}
		} catch (RuntimeException re) {
			LOGGER.error("find by actId and userId failed", re);
			throw re;
		}
		
		return userAct;
	}

	// 删除某个userAct对象
	@Override
	public void delete(UserAct persistentInstance) {
		LOGGER.debug("deleting UserAct instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOGGER.debug("delete successful");
		} catch (RuntimeException re) {
			LOGGER.error("delete failed", re);
			throw re;
		}
	}

	// 保存某个userAct对象
	@Override
	public void save(UserAct oneUserActPO) {
		LOGGER.debug("saving UserAct instance");
		try {
			getHibernateTemplate().save(oneUserActPO);
			LOGGER.debug("save successful");
		} catch (RuntimeException re) {
			LOGGER.error("save failed", re);
			throw re;
		}

	}

	// 更新某个userAct对象
	@Override
	public void merge(UserAct detachedInstance) {
		LOGGER.debug("merging UserAct instance");
		try {
			UserAct result = (UserAct) getHibernateTemplate().merge(
					detachedInstance);
			LOGGER.debug("merge successful");
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

}
