package com.tu.model.group;

import java.util.List;

public interface GroupInterface {

	GroupVO dogetOneGroup(Integer userId);

	List<GroupVO> dogetAllGroup();

	GroupVO dogetOneGroupByName(String groupName);

}
