package com.tu.user.act.model;

public interface UserActInterface {

	String doDeleteOneUser_act(Integer userId, Integer actId);

	String doAddOneUser_act(UserActVO oneUserActVO);

	UserActVO doGetOneActById(Integer userId, Integer updateActId);

	void doUpdateOneUser_act(UserActVO oneuserAct);

}
