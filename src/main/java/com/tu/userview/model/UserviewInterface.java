package com.tu.userview.model;

import java.util.List;

import com.tu.userlogin.model.UserloginVO;

public interface UserviewInterface {

	public UserviewVO doGetOneUserviewInfoByUserId(Integer userID);

	public List<UseractsVO> doGetAllUserActsByUserId(Integer userId,
			Integer year);

	public List<UserMsgVO> dogetMessages(Integer userId);

	public String doupdateOneuserInfo(UserloginVO userInfo);

}
