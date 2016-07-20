package com.tu.user.msg.dao;

import java.util.List;

public interface UserMsgDAOInterface {

	public void save(UserMsg oneUserMsgPO);

	public UserMsg merge(UserMsg detachedInstance);

	public void delete(UserMsg userGroupPO);

	public List<UserMsg> findMsgByUserId(Integer userId);

	public UserMsg findByUserIdAndMsgId(Integer userId, Integer msgId);

}
