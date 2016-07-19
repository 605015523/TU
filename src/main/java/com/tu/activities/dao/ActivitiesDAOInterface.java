package com.tu.activities.dao;

import java.util.List;

public interface ActivitiesDAOInterface {

	public Integer save(Activities oneActivitiesPO);

	public void delete(Activities oneActivitiesPO);

	public List<Activities> findAll();

	public Activities findById(Integer actId);

	public List<Activities> findByGroupId(Integer groupId);

	public Activities merge(Activities oneActPO);
}
