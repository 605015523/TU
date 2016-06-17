package com.tu.group.model;

import java.util.ArrayList;

public interface GroupInterface {

	GroupVO dogetOneGroup(Integer userId);

	ArrayList<GroupVO> dogetAllGroup();

	GroupVO dogetOneGroupByName(String groupName);

}
