package com.tu.user.msg.dao;

import java.util.List;

public interface User_msgDAOInterface {

	public void save(User_msg oneUserMsgPO);

	public User_msg merge(User_msg detachedInstance);

	public void delete(User_msg userGroupPO);

	public List<User_msg> findMsgByUserId(Integer userId);

	public User_msg findByUserIdAndMsgId(Integer userId, Integer msgId);

}
