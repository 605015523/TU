package com.tu.activities.dao;

import java.util.List;

public interface ActivityDAOInterface {

	public Integer save(Activity oneActivityPO);

	public void delete(Activity oneActivityPO);

	public List<Activity> findAll();

	public Activity findById(Integer actId);

	public List<Activity> findByGroupId(Integer groupId);

	public Activity merge(Activity oneActPO);
}
