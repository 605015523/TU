package com.tu.model.messages;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.messages.Messages;
import com.tu.dao.messages.MessagesDAOInterface;

public class MessagesImple extends Observable implements MessagesInterface {

	private static final Log LOG = LogFactory.getLog(MessagesImple.class);
	private MessagesDAOInterface msgDAO = null;

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
		Messages oneMessagesPO = msgDAO.findById(msgId);
		try {
			BeanUtils.copyProperties(oneMessagesVO, oneMessagesPO);
		} catch (IllegalAccessException e) {
			LOG.error(" there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException");
		}
		return oneMessagesVO;
	}

	// 添加一个messages
	@Override
	public Integer doAddOneMsg(MessagesVO oneMessagesVO) {
		Messages onemsgPO = new Messages();
		String addMessage = null;
		Integer msgId = null;

		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(onemsgPO, oneMessagesVO);
		} catch (IllegalAccessException e) {
			LOG.error("在MaterialImple类的doAddOneMaterial方法中利用BeanUtils类进行对象拷贝时出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			LOG.error("在MaterialImple类的doAddOneMaterial方法中利用BeanUtils类进行对象拷贝时出现了InvocationTargetException异常");
		}

		try {
			msgId = (Integer) msgDAO.save(onemsgPO);
		} catch (Exception e) {
			addMessage = e.toString();
		}
		return msgId;
	}

}
