package com.tu.model.userview;

import java.util.List;

import com.tu.model.userlogin.UserloginVO;

public interface UserviewInterface {

	UserviewVO doGetOneUserviewInfoByUserId(Integer userID);

	List<UserActDetailedVO> doGetAllUserActsByUserId(Integer userId,
			Integer year);
	
	UserActDetailedVO doGetUserActsByUserIdAndActId(Integer userId,
			Integer actId);
	
	List<UserMsgVO> dogetMessages(Integer userId);

	String doupdateOneuserInfo(UserloginVO userInfo);

}
