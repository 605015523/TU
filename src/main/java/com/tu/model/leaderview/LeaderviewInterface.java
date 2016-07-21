package com.tu.model.leaderview;

import java.util.List;

public interface LeaderviewInterface {
	List<GroupActVO> doGetAllUserActsByGroupId(Integer groupId);
	
	GroupActVO doGetUserActById(Integer actId);
}
