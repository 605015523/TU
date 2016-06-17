package com.tu.group.dao;

import java.util.List;

public interface GroupDAOInterface {

	public List findByProperty(String propertyName, Object value);

	public Group findById(Integer onegroupId);

	public List findAll();

	public Group findByUserId(Integer userId);

	public Group findByGroupName(String groupName);
}
