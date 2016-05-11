package user_act.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class User_actDAO extends HibernateDaoSupport implements
		User_actDAOInterface {
	private static final Log log = LogFactory.getLog(User_actDAO.class);
	public static final String USER_ID = "user_id";
	public static final String ACT_ID = "act_id";
	public static final String PARTICIPATER_NO = "participater_number";
	public static final String CONSUMPTION = "consumption";
	public static final String STATE = "state";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	// ͨ���û�Id��ȡ����user_act�����������Ի�ȡ�����û�����Ļ
	public List findByUserId(java.lang.Integer user_id) {
		try {

			return getHibernateTemplate().find(
					"from user_act.dao.User_act where user_id = ?", user_id);
		} catch (RuntimeException re) {
			log.error("find by userId failed", re);
			throw re;
		}
	}

	// ͨ��actId��ȡ����user_act�����������Ի�ȡ���в���û���û�
	public List findByActId(java.lang.Integer act_id) {
		try {

			return getHibernateTemplate().find(
					"from user_act.dao.User_act where act_id = ?", act_id);
		} catch (RuntimeException re) {
			log.error("find by userId failed", re);
			throw re;
		}
	}

	// ͨ��userId��actId���Ի�ȡ���ض�user_act����
	public User_act findByUserIdAndActId(Integer user_id, Integer act_id) {
		try {
			String hql = new String();
			hql = " from user_act.dao.User_act where user_id ="
					+ user_id.toString() + " and act_id=" + act_id.toString();
			User_act user_act = (User_act) this.getHibernateTemplate()
					.find(hql).get(0);

			return user_act;
		} catch (RuntimeException re) {
			log.error("find by actId and userId failed", re);
			throw re;
		}
	}

	// ɾ��ĳ��user_act����
	public void delete(User_act persistentInstance) {
		log.debug("deleting User_act instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	// ����ĳ��user_act����
	public void save(User_act oneUser_actPO) {
		log.debug("saving User_act instance");
		try {
			getHibernateTemplate().save(oneUser_actPO);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}

	}

	// ����ĳ��user_act����
	public void merge(User_act detachedInstance) {
		log.debug("merging User_act instance");
		try {
			User_act result = (User_act) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static User_actDAO getFromApplicationContext(ApplicationContext AIM) {
		return (User_actDAO) AIM.getBean("User_actDAO");
	}

}
