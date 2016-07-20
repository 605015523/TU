package com.tu.user.act.dao;

import java.util.List;

public interface UserActDAOInterface {

	List<UserAct> findByUserId(Integer userId);

	List<UserAct> findByActId(Integer actId);

	UserAct findByUserIdAndActId(Integer userId, Integer actId);

	void delete(UserAct userActPO);

	void save(UserAct userActPO);

	void merge(UserAct userActPO);

}
