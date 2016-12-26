package com.tu.model.userlogin;

import java.util.List;

import com.tu.model.userlogin.UserloginVO;

public interface UserloginManageInterface {

	List<UserloginVO> doGetAllUserlogin();

	UserloginVO dogetOneUserInfoByUserId(Integer userId);
	
	UserloginVO dogetOneUserInfoByUserName(String userName);

	void doUpdateOneUserInfo(UserloginVO userVO);
	
	void doCreateUser(UserloginVO userVO);

}
