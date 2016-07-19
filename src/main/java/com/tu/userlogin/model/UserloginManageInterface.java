package com.tu.userlogin.model;

import java.util.List;

import com.tu.userlogin.model.UserloginVO;

public interface UserloginManageInterface {

	public List<UserloginVO> doGetAllUserlogin();

	public UserloginVO dogetOneUserInfoByUserId(Integer userId);

	public void doUpdateOneUserInfo(UserloginVO oneUserVO);

}
