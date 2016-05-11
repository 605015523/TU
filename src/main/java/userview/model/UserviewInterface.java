package userview.model;

import java.util.ArrayList;
import java.util.List;

import userlogin.model.UserloginVO;

public interface UserviewInterface {

	public UserviewVO doGetOneUserviewInfoByUserId(Integer userID);

	public List<UseractsVO> doGetAllUserActsByUserId(Integer userId,
			Integer year);

	public ArrayList<User_msgVO> dogetMessages(Integer userId);

	public String doupdateOneuserInfo(UserloginVO userInfo);

}
