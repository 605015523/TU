package com.tu.model.user.msg;

import java.util.List;

import com.tu.dao.user.msg.UserMsg;

public interface UserMsgInterface {

	String doSendMsg(Integer msgId, List<Integer> alluserId);

	List<UserMsg> doGetUserMsg(Integer userId);

	UserMsg dogetOneByUserIdAndMsgId(Integer userId, Integer msgId);

	void doUpdateOneUserMsg(UserMsg oneUserMsg);

}
