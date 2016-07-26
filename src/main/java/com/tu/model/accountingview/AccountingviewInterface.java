package com.tu.model.accountingview;

import java.util.List;

import com.tu.model.activities.ActivityVO;
import com.tu.model.leaderview.GroupActVO;

public interface AccountingviewInterface {

	List<ActivityVO> doGetAllCheckValidateActs();

	List<UserGroupCostVO> doGetUserGroupCostsForValidatedActsByYear(Integer year);

	GroupActVO doGetGroupActivityByID(Integer oneactId);

	List<GroupActVO> doGetAllActsByGroupId(Integer groupId);

}
