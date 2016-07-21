package com.tu.model.user.act;

public interface UserActInterface {

	String doDeleteOneUserAct(Integer userId, Integer actId);

	String doAddOneUserAct(UserActVO oneUserActVO);

	UserActVO doGetOneActById(Integer userId, Integer updateActId);

	void doUpdateOneUserAct(UserActVO oneuserAct);

}
