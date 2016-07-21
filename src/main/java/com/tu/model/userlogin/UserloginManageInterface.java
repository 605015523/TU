package com.tu.model.userlogin;

import java.util.List;

import com.tu.model.userlogin.UserloginVO;

public interface UserloginManageInterface {

	public List<UserloginVO> doGetAllUserlogin();

	public UserloginVO dogetOneUserInfoByUserId(Integer userId);

	public void doUpdateOneUserInfo(UserloginVO oneUserVO);

}