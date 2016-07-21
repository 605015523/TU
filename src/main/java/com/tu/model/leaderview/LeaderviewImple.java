package com.tu.model.leaderview;

import java.text.SimpleDateFormat;
import java.util.*;

import com.tu.dao.activities.Activity;
import com.tu.dao.activities.ActivityDAOInterface;
import com.tu.dao.group.GroupDAOInterface;
import com.tu.dao.user.act.UserAct;
import com.tu.dao.user.act.UserActDAOInterface;
import com.tu.dao.user.group.UserGroupDAOInterface;
import com.tu.dao.userlogin.Userlogin;
import com.tu.dao.userlogin.UserloginDAOInterface;
import com.tu.util.ConfigurationConstants;

public class LeaderviewImple extends Observable implements LeaderviewInterface {

	private UserloginDAOInterface userloginDAO = null;
	private GroupDAOInterface groupDAO = null;
	private UserGroupDAOInterface userGroupDAO = null;
	private ActivityDAOInterface actsDAO = null;
	private UserActDAOInterface userActDAO = null;

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

	public UserActDAOInterface getUserActDAO() {
		return this.userActDAO;
	}

	public void setUserActDAO(UserActDAOInterface userActDAO) {
		this.userActDAO = userActDAO;
	}

	// 通过groupId获取所有参加这个活动的user
	@Override
	public List<GroupActVO> doGetAllUserActsByGroupId(Integer groupId) {
		List<Activity> acts = actsDAO.findByGroupId(groupId);
		List<GroupActVO> actsVO = new ArrayList<GroupActVO>();

		for (Activity act : acts) {
			actsVO.add(convertToVO(act));
		}
		Collections.reverse(actsVO);
		return actsVO;		
	}
	
	@Override
	public GroupActVO doGetUserActById(Integer actId) {
		Activity activity = actsDAO.findById(actId);
		return convertToVO(activity);
	}
	
	private GroupActVO convertToVO(Activity act) {
		GroupActVO actsPO = new GroupActVO();

		actsPO.setActId(act.getActId());
		actsPO.setActName(act.getActName());
		actsPO.setGroupId(act.getGroupId());
		actsPO.setActMoney(act.getActMoney());
		actsPO.setDescription(act.getDescription());
		actsPO.setState(act.getState());

		SimpleDateFormat formatter = new SimpleDateFormat(ConfigurationConstants.DATE_FORMAT);
		String daterange = formatter.format(act
				.getEnrollStartDate())
				+ " - "
				+ formatter.format(act.getEnrollEndDate());
		actsPO.setDaterange(daterange);
		actsPO.setActDate(formatter.format(act.getActDate()));
		actsPO.setComment(act.getComment());
		List<UserAct> useractPO = userActDAO.findByActId(act
				.getActId());
		List<MemberInVO> memberInVO = new ArrayList<MemberInVO>();
		float sum = 0;
		Integer nbParticipants = 0;
		for (int j = 0; j < useractPO.size(); j++) {
			MemberInVO oneMemberIn = new MemberInVO();
			oneMemberIn.setUserId(useractPO.get(j).getUserId());
			oneMemberIn.setNbParticipants(useractPO.get(j)
					.getNbParticipants());
			oneMemberIn.setConsumption(useractPO.get(j).getConsumption());
			oneMemberIn.setRemark(useractPO.get(j).getRemark());
			sum += useractPO.get(j).getConsumption();
			nbParticipants += useractPO.get(j).getNbParticipants();
			Userlogin userPO = userloginDAO.findById(useractPO.get(j).getUserId());
			oneMemberIn.setUserName(userPO.getUserName());
			oneMemberIn.setUserDept(userPO.getUserDept());
			memberInVO.add(oneMemberIn);
		}
		actsPO.setNbParticipants(nbParticipants);
		actsPO.setMemberInVO(memberInVO);
		actsPO.setSum(sum);
		
		return actsPO;
	}

}
