package com.tu.leaderview.model;

import java.text.SimpleDateFormat;
import java.util.*;

import com.tu.activities.dao.Activity;
import com.tu.activities.dao.ActivityDAOInterface;
import com.tu.group.dao.GroupDAOInterface;
import com.tu.user.act.dao.User_act;
import com.tu.user.act.dao.UseActDAOInterface;
import com.tu.user.group.dao.UserGroupDAOInterface;
import com.tu.userlogin.dao.Userlogin;
import com.tu.userlogin.dao.UserloginDAOInterface;

public class LeaderviewImple extends Observable implements LeaderviewInterface {

	private UserloginDAOInterface userloginDAO = null;
	private GroupDAOInterface groupDAO = null;
	private UserGroupDAOInterface userGroupDAO = null;
	private ActivityDAOInterface actsDAO = null;
	private UseActDAOInterface userActDAO = null;

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

	public UserGroupDAOInterface getUserGroupDAO() {
		return userGroupDAO;
	}

	public void setUserGroupDAO(UserGroupDAOInterface userGroupDAO) {
		this.userGroupDAO = userGroupDAO;
	}

	public ActivityDAOInterface getActsDAO() {
		return this.actsDAO;
	}

	public void setActsDAO(ActivityDAOInterface actsDAO) {
		this.actsDAO = actsDAO;
	}

	public UseActDAOInterface getUserActDAO() {
		return this.userActDAO;
	}

	public void setUserActDAO(UseActDAOInterface userActDAO) {
		this.userActDAO = userActDAO;
	}

	// 通过groupId获取所有参加这个活动的user
	@Override
	public List<GroupActVO> doGetAllUserActsByGroupId(Integer groupId) {
		List<Activity> acts = actsDAO.findByGroupId(groupId);
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
			List<User_act> useractPO = userActDAO.findByActId(acts.get(i)
					.getActId());
			List<MemberInVO> memberInVO = new ArrayList<MemberInVO>();
			float sum = 0;
			Integer participatorNO = 0;
			for (int j = 0; j < useractPO.size(); j++) {
				MemberInVO oneMemberIn = new MemberInVO();
				oneMemberIn.setUserId(useractPO.get(j).getUserId());
				oneMemberIn.setParticipatorNO(useractPO.get(j)
						.getParticipatorNO());
				oneMemberIn.setConsumption(useractPO.get(j).getConsumption());
				oneMemberIn.setRemark(useractPO.get(j).getRemark());
				sum += useractPO.get(j).getConsumption();
				participatorNO += useractPO.get(j).getParticipatorNO();
				Userlogin userPO = userloginDAO.findById(useractPO.get(j).getUserId());
				oneMemberIn.setUserName(userPO.getUserName());
				oneMemberIn.setUserDept(userPO.getUserDept());
				memberInVO.add(oneMemberIn);
			}
			actsPO.setParticipatorNO(participatorNO);
			actsPO.setMemberInVO(memberInVO);
			actsPO.setSum(sum);

			actsVO.add(actsPO);

		}
		List<GroupActVO> inverseactsVO = new ArrayList<GroupActVO>();
		for (int j = actsVO.size()-1; j >= 0; j--) {
			inverseactsVO.add(actsVO.get(j));
		}
		return inverseactsVO;

	}

}
