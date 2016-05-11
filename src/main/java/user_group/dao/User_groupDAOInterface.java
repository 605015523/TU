package user_group.dao;

import java.util.List;

public interface User_groupDAOInterface {

	public void save(User_group oneUser_groupPO);

	public List findByUserId(Integer user_id);

	public List<User_group> findByGroupId(Integer groupId);

	public User_group findByGroupIdAndUserId(Integer groupId, Integer userId);

	public void delete(User_group userGroupPO);

}
