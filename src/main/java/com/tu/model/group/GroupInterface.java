package com.tu.model.group;

import java.util.List;

public interface GroupInterface {

	GroupVO doGetOneGroupByLeaderId(Integer leaderId);

	List<GroupVO> doGetAllGroup();

	GroupVO doGetOneGroupById(Integer groupId);

}
