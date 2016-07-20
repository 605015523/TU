package com.tu.accountingview.model;

import java.util.List;

import com.tu.leaderview.model.GroupActVO;

public interface AccountingviewInterface {

	List<GroupActVO> doGetAllCheckValidateActs();

	List<UserGroupCostVO> doGetAllActsByYear(Integer year);

	GroupActVO doGetAllValidateDetails(Integer oneactId);

	List<GroupActVO> doGetAllActsByGroupId(Integer groupId);

}
