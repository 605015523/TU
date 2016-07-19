package com.tu.user_msg.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;

import com.tu.user_msg.dao.User_msg;
import com.tu.user_msg.dao.User_msgDAOInterface;

public class User_msgImple extends Observable implements User_msgInterface {

	private User_msgDAOInterface user_msgDAO = null;

	public User_msgImple() {

	}

	public User_msgDAOInterface getUser_msgDAO() {
		return this.user_msgDAO;
	}

	public void setUser_msgDAO(User_msgDAOInterface user_msgDAO) {
		this.user_msgDAO = user_msgDAO;
	}

	// 删除一个user_msg对象
	public String doDeleteOneUser_msg(Integer user_id, Integer msg_id) {
		String OkOrNot = null;
		User_msg user_msgPO = new User_msg();
		user_msgPO = user_msgDAO.findByUserIdAndMsgId(user_id, msg_id);
		try {
			if (user_msgDAO.findByUserIdAndMsgId(user_id, msg_id) != null) {
				user_msgDAO.delete(user_msgPO);
				OkOrNot = "delete success!";
			} else {
				OkOrNot = "delete fail!";
			}
		} catch (Exception e) {
			OkOrNot = e.toString();
		}
		return OkOrNot;

	}

	// 发送一个msg
	public String doSendMsg(Integer msgId, List<Integer> alluserId) {
		User_msg onemsgPO = new User_msg();
		String sendMessage = null;
		for (int i = 0; i < alluserId.size(); i++) {
			User_msg oneUser_magVO = new User_msg();
			oneUser_magVO.setUserId((Integer) alluserId.get(i));
			oneUser_magVO.setMsgId(msgId);
			oneUser_magVO.setReadState("new");
			user_msgDAO.save(oneUser_magVO);
		}
		return null;
	}

	// 获取所有用户的msg
	public List<User_msg> doGetUserMsg(Integer userId) {
		List<User_msg> user_msgVO = new ArrayList<User_msg>();
		user_msgVO = user_msgDAO.findMsgByUserId(userId);
		return user_msgVO;
	}

	// 通过userId和msgId获取一个User_msg对象
	public User_msg dogetOneByUserIdAndMsgId(Integer userId, Integer msgId) {
		User_msg oneUser_msg = new User_msg();
		oneUser_msg = user_msgDAO.findByUserIdAndMsgId(userId, msgId);
		return oneUser_msg;
	}

	// 更新一个user_msg对象
	public void doUpdateOneUser_msg(User_msg oneUser_msgVO) {
		User_msg oneUser_msgPO = new User_msg();
		Integer userId = oneUser_msgVO.getUserId();
		Integer msgId = oneUser_msgVO.getMsgId();
		oneUser_msgPO = user_msgDAO.findByUserIdAndMsgId(userId, msgId);
		String OkOrNot = null;
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneUser_msgPO, oneUser_msgVO);
		} catch (IllegalAccessException e) {
			System.out
					.println("there is a IllegalAccessException while copy user_msg in doUpdateOneUser_msg.");
		} catch (InvocationTargetException e) {
			System.out
					.println("there is a InvocationTargetException while copy user_msg in doUpdateOneUser_msg.");
		}
		try {
			user_msgDAO.merge(oneUser_msgPO);
			OkOrNot = "merge success!";
		} catch (Exception e) {
			OkOrNot = e.toString();
		}

	}

}
