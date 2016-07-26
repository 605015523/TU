package com.tu.dao.user.group;

import java.util.List;

public interface UserGroupDAOInterface {

	void save(UserGroup oneUserGroupPO);

	List<UserGroup> findByUserId(Integer userId);

	List<UserGroup> findByGroupId(Integer groupId);

	UserGroup findByGroupIdAndUserId(Integer groupId, Integer userId);

	void delete(UserGroup userGroupPO);

}
