package com.tu.model.userview;

import java.util.List;

import com.tu.model.userlogin.UserloginVO;

public interface UserviewInterface {

	public UserviewVO doGetOneUserviewInfoByUserId(Integer userID);

	public List<UserActDetailedVO> doGetAllUserActsByUserId(Integer userId,
			Integer year);

	public List<UserMsgVO> dogetMessages(Integer userId);

	public String doupdateOneuserInfo(UserloginVO userInfo);

}
