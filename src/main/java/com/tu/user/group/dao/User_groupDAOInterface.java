package com.tu.user.group.dao;

import java.util.List;

public interface User_groupDAOInterface {

	public void save(User_group oneUserGroupPO);

	public List<User_group> findByUserId(Integer userId);

	public List<User_group> findByGroupId(Integer groupId);

	public User_group findByGroupIdAndUserId(Integer groupId, Integer userId);

	public void delete(User_group userGroupPO);

}
