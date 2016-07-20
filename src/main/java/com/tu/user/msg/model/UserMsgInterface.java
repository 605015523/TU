package com.tu.user.msg.model;

import java.util.List;

import com.tu.user.msg.dao.UserMsg;

public interface UserMsgInterface {

	String doSendMsg(Integer msgId, List<Integer> alluserId);

	List<UserMsg> doGetUserMsg(Integer userId);

	UserMsg dogetOneByUserIdAndMsgId(Integer userId, Integer msgId);

	void doUpdateOneUserMsg(UserMsg oneUserMsg);

}
