package com.tu.model.user.msg;

import java.util.List;

import com.tu.model.userview.UserMsgVO;

public interface UserMsgInterface {

	String doSendMsg(Integer msgId, List<Integer> alluserId);

	List<UserMsgVO> doGetUserMsgs(Integer userId);

	UserMsgVO doGetOneByUserIdAndMsgId(Integer userId, Integer msgId);

	void doUpdateOneUserMsg(UserMsgVO oneUserMsg);
	
	Integer countNewMsgs(Integer userId);

}
