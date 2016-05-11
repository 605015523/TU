package messages.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import org.apache.commons.beanutils.BeanUtils;

import messages.dao.Messages;
import messages.dao.MessagesDAOInterface;

public class MessagesImple extends Observable implements MessagesInterface {

	private MessagesDAOInterface msgDAO = null;

	public MessagesDAOInterface getMsgDAO() {
		return this.msgDAO;
	}

	public void setMsgDAO(MessagesDAOInterface msgDAO) {
		this.msgDAO = msgDAO;
	}

	public MessagesImple() {
		// ���췽��
	}

	// ͨ��messagesId��ȡ��messages����
	public MessagesVO doGetOneMsgById(Integer msgId) {
		MessagesVO oneMessagesVO = new MessagesVO();
		Messages oneMessagesPO = new Messages();
		oneMessagesPO = msgDAO.findById(msgId);
		try {
			BeanUtils.copyProperties(oneMessagesVO, oneMessagesPO);
		} catch (IllegalAccessException e) {
			System.out.println(" there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			System.out.println("there is a InvocationTargetException");
		}
		return oneMessagesVO;
	}

	// ���һ��messages
	public Integer doAddOneMsg(MessagesVO oneMessagesVO) {
		Messages onemsgPO = new Messages();
		String addMessage = null;
		Integer msgId = null;

		try { // ����Bean������ʵ�ּ򵥵ؿ���
			BeanUtils.copyProperties(onemsgPO, oneMessagesVO);
		} catch (IllegalAccessException e) {
			System.out
					.println("��MaterialImple���doAddOneMaterial����������BeanUtils����ж��󿽱�ʱ������IllegalAccessException�쳣");
		} catch (InvocationTargetException e) {
			System.out
					.println("��MaterialImple���doAddOneMaterial����������BeanUtils����ж��󿽱�ʱ������InvocationTargetException�쳣");
		}

		try {
			msgId = (Integer) msgDAO.save(onemsgPO);

		} catch (Exception e) {
			addMessage = e.toString();
		}
		return msgId;
	}

}
