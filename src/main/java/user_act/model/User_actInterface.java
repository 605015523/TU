package user_act.model;

public interface User_actInterface {

	String doDeleteOneUser_act(Integer userId, Integer actId);

	String doAddOneUser_act(User_actVO oneUserActVO);

	User_actVO doGetOneActById(Integer userId, Integer updateActId);

	void doUpdateOneUser_act(User_actVO oneuserAct);

}
