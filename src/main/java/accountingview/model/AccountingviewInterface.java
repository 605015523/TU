package accountingview.model;

import java.util.List;

public interface AccountingviewInterface {

	List<GroupActVO> doGetAllCheckValidateActs();

	List<UserGroupCostVO> doGetAllActsByYear(Integer year);

	GroupActVO doGetAllValidateDetails(Integer oneactId);

	List<GroupActVO> doGetAllActsByGroupId(Integer groupId);

}
