package com.tu.user.msg.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.user.msg.dao.User_msg;
import com.tu.user.msg.dao.User_msgDAOInterface;

public class User_msgImple extends Observable implements User_msgInterface {

	private static final Log LOGGER = LogFactory.getLog(User_msgImple.class);
			
	private User_msgDAOInterface user_msgDAO = null;

	public User_msgImple() {

	}

	public User_msgDAOInterface getUser_msgDAO() {
		return this.user_msgDAO;
	}

	public void setUser_msgDAO(User_msgDAOInterface userMsgDAO) {
		this.user_msgDAO = userMsgDAO;
	}

	// 删除一个user_msg对象
	public String doDeleteOneUser_msg(Integer userId, Integer msgId) {
		String okOrNot = null;
		User_msg userMsgPO = user_msgDAO.findByUserIdAndMsgId(userId, msgId);
		try {
			if (userMsgPO != null) {
				user_msgDAO.delete(userMsgPO);
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
	public String doSendMsg(Integer msgId, List<Integer> alluserId) {
		User_msg onemsgPO = new User_msg();
		String sendMessage = null;
		for (int i = 0; i < alluserId.size(); i++) {
			User_msg oneUserMagVO = new User_msg();
			oneUserMagVO.setUserId((Integer) alluserId.get(i));
			oneUserMagVO.setMsgId(msgId);
			oneUserMagVO.setReadState("new");
			user_msgDAO.save(oneUserMagVO);
		}
		return null;
	}

	// 获取所有用户的msg
	public List<User_msg> doGetUserMsg(Integer userId) {
		return user_msgDAO.findMsgByUserId(userId);
	}

	// 通过userId和msgId获取一个User_msg对象
	public User_msg dogetOneByUserIdAndMsgId(Integer userId, Integer msgId) {
		User_msg oneUserMsg = new User_msg();
		oneUserMsg = user_msgDAO.findByUserIdAndMsgId(userId, msgId);
		return oneUserMsg;
	}

	// 更新一个user_msg对象
	public void doUpdateOneUser_msg(User_msg oneUserMsgVO) {
		Integer userId = oneUserMsgVO.getUserId();
		Integer msgId = oneUserMsgVO.getMsgId();
		User_msg oneUserMsgPO = user_msgDAO.findByUserIdAndMsgId(userId, msgId);
		String okOrNot = null;
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneUserMsgPO, oneUserMsgVO);
		} catch (IllegalAccessException e) {
			LOGGER.error("there is a IllegalAccessException while copy user_msg in doUpdateOneUser_msg.");
		} catch (InvocationTargetException e) {
			LOGGER.error("there is a InvocationTargetException while copy user_msg in doUpdateOneUser_msg.");
		}
		try {
			user_msgDAO.merge(oneUserMsgPO);
			okOrNot = "merge success!";
		} catch (Exception e) {
			okOrNot = e.toString();
		}

	}

}
