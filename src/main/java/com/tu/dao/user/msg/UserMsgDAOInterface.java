package com.tu.dao.user.msg;

import java.util.List;

public interface UserMsgDAOInterface {

	void save(UserMsg oneUserMsgPO);

	UserMsg merge(UserMsg detachedInstance);

	void delete(UserMsg userGroupPO);

	List<UserMsg> findMsgByUserId(Integer userId);

	UserMsg findByUserIdAndMsgId(Integer userId, Integer msgId);

}
