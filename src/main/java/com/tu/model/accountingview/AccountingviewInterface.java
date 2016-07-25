package com.tu.model.accountingview;

import java.util.List;

import com.tu.model.leaderview.GroupActVO;

public interface AccountingviewInterface {

	List<GroupActVO> doGetAllCheckValidateActs();

	List<UserGroupCostVO> doGetAllActsByYear(Integer year);

	GroupActVO doGetGroupActivityByID(Integer oneactId);

	List<GroupActVO> doGetAllActsByGroupId(Integer groupId);

}
