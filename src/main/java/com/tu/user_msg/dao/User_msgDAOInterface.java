package com.tu.user_msg.dao;

import java.util.List;

public interface User_msgDAOInterface {

	public void save(User_msg oneUser_msgPO);

	public User_msg merge(User_msg detachedInstance);

	public void delete(User_msg userGroupPO);

	public List findMsgByUserId(Integer userId);

	public User_msg findByUserIdAndMsgId(Integer userId, Integer msgId);

}
