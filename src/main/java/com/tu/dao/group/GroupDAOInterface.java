package com.tu.dao.group;

import java.util.List;

public interface GroupDAOInterface {

	List<Group> findByProperty(String propertyName, Object value);

	Group findById(Integer onegroupId);

	List<Group> findAll();

	Group findByUserId(Integer userId);

	Group findByGroupName(String groupName);
}
