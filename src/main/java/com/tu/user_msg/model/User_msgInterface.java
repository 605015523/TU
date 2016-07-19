package com.tu.user_msg.model;

import java.util.List;

import com.tu.user_msg.dao.User_msg;

public interface User_msgInterface {

	String doSendMsg(Integer msgId, List<Integer> alluserId);

	List<User_msg> doGetUserMsg(Integer userId);

	User_msg dogetOneByUserIdAndMsgId(Integer userId, Integer msgId);

	void doUpdateOneUser_msg(User_msg oneUserMsg);

}
