package com.tu.dao.user.group;

import java.util.List;

public interface UserGroupDAOInterface {

	public void save(UserGroup oneUserGroupPO);

	public List<UserGroup> findByUserId(Integer userId);

	public List<UserGroup> findByGroupId(Integer groupId);

	public UserGroup findByGroupIdAndUserId(Integer groupId, Integer userId);

	public void delete(UserGroup userGroupPO);

}
