package com.tu.model.user.msg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.group.GroupDAOInterface;
import com.tu.dao.messages.MessagesDAOInterface;
import com.tu.dao.user.msg.UserMsg;
import com.tu.dao.user.msg.UserMsgDAOInterface;
import com.tu.mapper.DAOModelMapper;
import com.tu.model.userview.UserMsgVO;

public class UserMsgImple extends Observable implements UserMsgInterface {

	private static final Log LOGGER = LogFactory.getLog(UserMsgImple.class);
	
	private DAOModelMapper daoModelMapper;
	private UserMsgDAOInterface userMsgDAO = null;
	private MessagesDAOInterface msgDAO = null;
	private GroupDAOInterface groupDAO = null;
	
	public UserMsgImple() {
		// do nothing
	}

	// 删除一个user_msg对象
	public String doDeleteOneUserMsg(Integer userId, Integer msgId) {
		String okOrNot = null;
		UserMsg userMsgPO = userMsgDAO.findByUserIdAndMsgId(userId, msgId);
		try {
			if (userMsgPO != null) {
				userMsgDAO.delete(userMsgPO);
				okOrNot = "delete success!";
			} else {
				okOrNot = "delete fail!";
			}
		} catch (Exception e) {
			okOrNot = e.toString();
		}
		return okOrNot;

	}

	// 发送一个msg
	@Override
	public String doSendMsg(Integer msgId, List<Integer> alluserId) {
		for (Integer i : alluserId) {
			UserMsg oneUserMagVO = new UserMsg();
			oneUserMagVO.setUserId(i);
			oneUserMagVO.setMsgId(msgId);
			oneUserMagVO.setReadState(UserMsgConstants.STATE_NEW);
			userMsgDAO.save(oneUserMagVO);
		}
		return null;
	}

	// 获取所有用户的msg
	@Override
	public List<UserMsgVO> doGetUserMsgs(Integer userId) {
		List<UserMsgVO> userMsgsVO = new ArrayList<UserMsgVO>();
		List<UserMsg> userMsgs = userMsgDAO.findMsgByUserId(userId);
		
		for (UserMsg userMsg : userMsgs) {
			userMsgsVO.add(daoModelMapper.convertoUserMsgToUserMsgVO(userMsg));
		}
		
		Collections.reverse(userMsgsVO);
		return userMsgsVO;
	}
	
	// 通过userId和msgId获取一个UserMsg对象
	@Override
	public UserMsgVO doGetOneByUserIdAndMsgId(Integer userId, Integer msgId) {
		UserMsg oneUserMsg = userMsgDAO.findByUserIdAndMsgId(userId, msgId);
		return daoModelMapper.convertoUserMsgToUserMsgVO(oneUserMsg);
	}
	
	
	// 更新一个userMsg对象
	@Override
	public void doUpdateOneUserMsg(UserMsgVO oneUserMsgVO) {
		Integer userId = oneUserMsgVO.getUserId();
		Integer msgId = oneUserMsgVO.getMessage().getMsgId();
		UserMsg oneUserMsgPO = userMsgDAO.findByUserIdAndMsgId(userId, msgId);
		String okOrNot = null;
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneUserMsgPO, oneUserMsgVO);
		} catch (IllegalAccessException e) {
			LOGGER.error("there is a IllegalAccessException while copy userMsg in doUpdateOneUserMsg: " + e.toString());
		} catch (InvocationTargetException e) {
			LOGGER.error("there is a InvocationTargetException while copy userMsg in doUpdateOneUserMsg: " + e.toString());
		}
		try {
			userMsgDAO.merge(oneUserMsgPO);
			okOrNot = "merge success!";
		} catch (Exception e) {
			okOrNot = e.toString();
		}

	}

	@Override
	public Integer countNewMsgs(Integer userId) {
		Integer newMsg = 0;
		List<UserMsg> userMsgs = userMsgDAO.findMsgByUserId(userId);
		for (UserMsg userMsg : userMsgs) {
			if (userMsg.getReadState().equals(UserMsgConstants.STATE_NEW)) {
				newMsg += 1;
			}
		}
		
		return newMsg;
	}
	
	// Getters and setters
	public UserMsgDAOInterface getUserMsgDAO() {
		return this.userMsgDAO;
	}

	public void setUserMsgDAO(UserMsgDAOInterface userMsgDAO) {
		this.userMsgDAO = userMsgDAO;
	}

	public MessagesDAOInterface getMsgDAO() {
		return msgDAO;
	}

	public void setMsgDAO(MessagesDAOInterface msgDAO) {
		this.msgDAO = msgDAO;
	}

	public GroupDAOInterface getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(GroupDAOInterface groupDAO) {
		this.groupDAO = groupDAO;
	}

	public DAOModelMapper getDaoModelMapper() {
		return daoModelMapper;
	}

	public void setDaoModelMapper(DAOModelMapper daoModelMapper) {
		this.daoModelMapper = daoModelMapper;
	}

}
