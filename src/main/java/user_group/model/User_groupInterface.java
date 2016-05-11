package user_group.model;

import java.util.List;

public interface User_groupInterface {

	String doDeleteOneUser_group(Integer groupId, Integer userId);

	List<User_groupVO> doGetAllMembersId(Integer groupId);

}
