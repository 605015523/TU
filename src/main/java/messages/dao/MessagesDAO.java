package messages.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MessagesDAO extends HibernateDaoSupport implements
		MessagesDAOInterface {
	private static final Log log = LogFactory.getLog(MessagesDAO.class);
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

	protected void initDao() {
		// do nothing
	}

	// 获取所有的messages
	public List findAll() {
		log.debug("finding all Message instances");
		try {
			String queryString = "from Messages";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	// 通过messagesId来获取特定的messages对象
	public Messages findById(java.lang.Integer msg_id) {
		log.debug("getting Message instance with id: " + msg_id);
		try {
			Messages instance = (Messages) getHibernateTemplate().get(
					"messages.dao.Messages", msg_id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	// 保存一个messages对象
	public Integer save(Messages oneMessagesPO) {
		log.debug("saving message instance");
		try {
			getHibernateTemplate().save(oneMessagesPO);
			log.debug("save successful");
			return oneMessagesPO.getMsgId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}

	}

	public static MessagesDAO getFromApplicationContext(ApplicationContext aim) {
		return (MessagesDAO) aim.getBean("MessagesDAO");
	}

}
