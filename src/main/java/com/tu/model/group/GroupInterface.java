package com.tu.model.group;

import java.util.List;

public interface GroupInterface {

	GroupVO doGetOneGroup(Integer userId);

	List<GroupVO> doGetAllGroup();

	GroupVO doGetOneGroupByName(String groupName);

}
