package com.tu.leaderview.model;

import java.text.SimpleDateFormat;
import java.util.*;

import com.tu.activities.dao.Activity;
import com.tu.activities.dao.ActivityDAOInterface;
import com.tu.group.dao.GroupDAOInterface;
import com.tu.user_act.dao.User_act;
import com.tu.user_act.dao.User_actDAOInterface;
import com.tu.user_group.dao.User_groupDAOInterface;
import com.tu.userlogin.dao.Userlogin;
import com.tu.userlogin.dao.UserloginDAOInterface;

public class LeaderviewImple extends Observable implements LeaderviewInterface {

	private UserloginDAOInterface userloginDAO = null;
	private GroupDAOInterface groupDAO = null;
	private User_groupDAOInterface user_groupDAO = null;
	private ActivityDAOInterface actsDAO = null;
	private User_actDAOInterface user_actDAO = null;

	public LeaderviewImple() {

	}

	public UserloginDAOInterface getUserloginDAO() {
		return this.userloginDAO;
	}

	public void setUserloginDAO(UserloginDAOInterface userloginDAO) {
		this.userloginDAO = userloginDAO;
	}

	public GroupDAOInterface getGroupDAO() {
		return this.groupDAO;
	}

	public void setGroupDAO(GroupDAOInterface groupDAO) {
		this.groupDAO = groupDAO;
	}

	public User_groupDAOInterface getUser_groupDAO() {
		return user_groupDAO;
	}

	public void setUser_groupDAO(User_groupDAOInterface userGroupDAO) {
		user_groupDAO = userGroupDAO;
	}

	public ActivityDAOInterface getActsDAO() {
		return this.actsDAO;
	}

	public void setActsDAO(ActivityDAOInterface actsDAO) {
		this.actsDAO = actsDAO;
	}

	public User_actDAOInterface getUser_actDAO() {
		return this.user_actDAO;
	}

	public void setUser_actDAO(User_actDAOInterface userActDAO) {
		this.user_actDAO = userActDAO;
	}

	// 通过groupId获取所有参加这个活动的user
	public List<GroupActVO> doGetAllUserActsByGroupId(Integer groupId) {
		List<Activity> acts = new ArrayList<Activity>();

		acts = actsDAO.findByGroupId(groupId);
		List<GroupActVO> actsVO = new ArrayList<GroupActVO>();

		for (int i = 0; i < acts.size(); i++) {
			GroupActVO actsPO = new GroupActVO();

			actsPO.setActId(acts.get(i).getActId());
			actsPO.setActName(acts.get(i).getActName());
			actsPO.setGroupId(acts.get(i).getGroupId());
			actsPO.setActMoney(acts.get(i).getActMoney());
			actsPO.setDescription(acts.get(i).getDescription());
			actsPO.setState(acts.get(i).getState());

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			String daterange = formatter.format(acts.get(i)
					.getEnrollStartDate())
					+ " - "
					+ formatter.format(acts.get(i).getEnrollEndDate());
			actsPO.setDaterange(daterange);
			actsPO.setActDate(formatter.format(acts.get(i).getActDate()));
			actsPO.setComment(acts.get(i).getComment());
			List<User_act> useractPO = user_actDAO.findByActId(acts.get(i)
					.getActId());
			ArrayList<memberInVO> memberInVO = new ArrayList<memberInVO>();
			float sum = 0;
			Integer participatorNO = 0;
			for (int j = 0; j < useractPO.size(); j++) {
				memberInVO oneMemberIn = new memberInVO();
				oneMemberIn.setUserId(useractPO.get(j).getUserId());
				oneMemberIn.setParticipatorNO(useractPO.get(j)
						.getParticipatorNO());
				oneMemberIn.setConsumption(useractPO.get(j).getConsumption());
				oneMemberIn.setRemark(useractPO.get(j).getRemark());
				sum += useractPO.get(j).getConsumption();
				participatorNO += useractPO.get(j).getParticipatorNO();
				Userlogin userPO = new Userlogin();
				userPO = userloginDAO.findById(useractPO.get(j).getUserId());
				oneMemberIn.setUserName(userPO.getUserName());
				oneMemberIn.setUserDept(userPO.getUserDept());
				memberInVO.add(oneMemberIn);
			}
			actsPO.setParticipatorNO(participatorNO);
			actsPO.setMemberInVO(memberInVO);
			actsPO.setSum(sum);

			actsVO.add(actsPO);

		}
		List<GroupActVO> InverseactsVO = new ArrayList<GroupActVO>();
		for (int j = 0; j < actsVO.size(); j++) {
			InverseactsVO.add(actsVO.get(actsVO.size() - j - 1));
		}
		return InverseactsVO;

	}

}
