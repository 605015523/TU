package com.tu.dao.activities;

import java.util.List;

public interface ActivityDAOInterface {

	Integer save(Activity oneActivityPO);

	void delete(Activity oneActivityPO);

	List<Activity> findAll();
	
	List<Activity> findUpcoming();

	Activity findById(Integer actId);

	List<Activity> findByGroupId(Integer groupId);
	
	List<Activity> findByYear(Integer year);

	Activity merge(Activity oneActPO);
}
