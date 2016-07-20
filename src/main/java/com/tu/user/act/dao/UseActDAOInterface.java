package com.tu.user.act.dao;

import java.util.List;

public interface UseActDAOInterface {

	List<User_act> findByUserId(Integer userId);

	List<User_act> findByActId(Integer actId);

	User_act findByUserIdAndActId(Integer userId, Integer actId);

	void delete(User_act userActPO);

	void save(User_act userActPO);

	void merge(User_act userActPO);

}
