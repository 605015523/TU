package com.tu.user.group.model;

import java.util.List;

public interface UserGroupInterface {

	String doDeleteOneUserGroup(Integer groupId, Integer userId);

	List<UserGroupVO> doGetAllMembersId(Integer groupId);

}
