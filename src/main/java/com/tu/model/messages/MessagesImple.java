package com.tu.model.messages;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.messages.Message;
import com.tu.dao.messages.MessagesDAOInterface;
import com.tu.mapper.DAOModelMapper;

public class MessagesImple extends Observable implements MessagesInterface {

	private static final Log LOG = LogFactory.getLog(MessagesImple.class);
	private MessagesDAOInterface msgDAO = null;
	private DAOModelMapper daoModelMapper;

	public MessagesDAOInterface getMsgDAO() {
		return this.msgDAO;
	}

	public void setMsgDAO(MessagesDAOInterface msgDAO) {
		this.msgDAO = msgDAO;
	}

	public MessagesImple() {
		// 构造方法
	}

	// 通过messagesId获取该messages对象
	@Override
	public MessagesVO doGetOneMsgById(Integer msgId) {
		MessagesVO oneMessagesVO = new MessagesVO();
		Message oneMessagesPO = msgDAO.findById(msgId);
		try {
			BeanUtils.copyProperties(oneMessagesVO, oneMessagesPO);
		} catch (IllegalAccessException e) {
			LOG.error("there is a IllegalAccessException", e);
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException", e);
		}
		return oneMessagesVO;
	}

	// 添加一个messages
	@Override
	public Integer doAddOneMsg(MessagesVO oneMessagesVO) {
		Message onemsgPO = daoModelMapper.convertMesssageVOTOMessage(oneMessagesVO);
		Integer msgId = null;

		try {
			msgId = (Integer) msgDAO.save(onemsgPO);
		} catch (Exception e) {
			LOG.error(e);
		}
		return msgId;
	}

	public DAOModelMapper getDaoModelMapper() {
		return daoModelMapper;
	}

	public void setDaoModelMapper(DAOModelMapper daoModelMapper) {
		this.daoModelMapper = daoModelMapper;
	}
	
}
