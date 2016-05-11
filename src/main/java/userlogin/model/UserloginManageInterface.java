package userlogin.model;

import java.util.ArrayList;
import userlogin.model.UserloginVO;

public interface UserloginManageInterface {

	public ArrayList<UserloginVO> doGetAllUserlogin();

	public UserloginVO dogetOneUserInfoByUserId(Integer userId);

	public void doUpdateOneUserInfo(UserloginVO oneUserVO);

}
