package com.tu.user.msg.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.user.msg.dao.UserMsg;
import com.tu.user.msg.dao.UserMsgDAOInterface;

public class UserMsgImple extends Observable implements UserMsgInterface {

	private static final Log LOGGER = LogFactory.getLog(UserMsgImple.class);
			
	private UserMsgDAOInterface userMsgDAO = null;

	public UserMsgImple() {

	}

	public UserMsgDAOInterface getUserMsgDAO() {
		return this.userMsgDAO;
	}

	public void setUserMsgDAO(UserMsgDAOInterface userMsgDAO) {
		this.userMsgDAO = userMsgDAO;
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
		for (int i = 0; i < alluserId.size(); i++) {
			UserMsg oneUserMagVO = new UserMsg();
			oneUserMagVO.setUserId((Integer) alluserId.get(i));
			oneUserMagVO.setMsgId(msgId);
			oneUserMagVO.setReadState("new");
			userMsgDAO.save(oneUserMagVO);
		}
		return null;
	}

	// 获取所有用户的msg
	@Override
	public List<UserMsg> doGetUserMsg(Integer userId) {
		return userMsgDAO.findMsgByUserId(userId);
	}

	// 通过userId和msgId获取一个UserMsg对象
	@Override
	public UserMsg dogetOneByUserIdAndMsgId(Integer userId, Integer msgId) {
		UserMsg oneUserMsg = userMsgDAO.findByUserIdAndMsgId(userId, msgId);
		return oneUserMsg;
	}

	// 更新一个userMsg对象
	@Override
	public void doUpdateOneUserMsg(UserMsg oneUserMsgVO) {
		Integer userId = oneUserMsgVO.getUserId();
		Integer msgId = oneUserMsgVO.getMsgId();
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

}
