package com.tu.user_msg.model;

import java.util.ArrayList;
import java.util.List;

import com.tu.user_msg.dao.User_msg;

public interface User_msgInterface {

	String doSendMsg(Integer msgId, ArrayList<Integer> alluserId);

	List doGetUserMsg(Integer userId);

	User_msg dogetOneByUserIdAndMsgId(Integer userId, Integer msgId);

	void doUpdateOneUser_msg(User_msg oneUserMsg);

}
