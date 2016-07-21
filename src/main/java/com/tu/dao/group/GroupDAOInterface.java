package com.tu.dao.group;

import java.util.List;

public interface GroupDAOInterface {

	public List<Group> findByProperty(String propertyName, Object value);

	public Group findById(Integer onegroupId);

	public List<Group> findAll();

	public Group findByUserId(Integer userId);

	public Group findByGroupName(String groupName);
}
