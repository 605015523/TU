package com.tu.accountingview.model;

import java.text.SimpleDateFormat;
import java.util.*;

import com.tu.activities.dao.Activity;
import com.tu.activities.dao.ActivityDAOInterface;
import com.tu.activities.model.ActivitiesConstant;
import com.tu.group.dao.Group;
import com.tu.group.dao.GroupDAOInterface;
import com.tu.leaderview.model.memberInVO;
import com.tu.user_act.dao.User_act;
import com.tu.user_act.dao.User_actDAOInterface;
import com.tu.userlogin.dao.Userlogin;
import com.tu.userlogin.dao.UserloginDAOInterface;

public class AccountingviewImple extends Observable implements
		AccountingviewInterface {

	private UserloginDAOInterface userloginDAO = null;
	private ActivityDAOInterface actsDAO = null;
	private User_actDAOInterface user_actDAO = null;
	private GroupDAOInterface groupDAO = null;

	public AccountingviewImple() {

	}

	public UserloginDAOInterface getUserloginDAO() {
		return this.userloginDAO;
	}

	public void setUserloginDAO(UserloginDAOInterface userloginDAO) {
		this.userloginDAO = userloginDAO;
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

	public GroupDAOInterface getGroupDAO() {
		return this.groupDAO;
	}

	public void setGroupDAO(GroupDAOInterface groupDAO) {
		this.groupDAO = groupDAO;
	}

	// 通过选择year的方式显示所有用户这一年的活动参与情况的实现细节
	public List<UserGroupCostVO> doGetAllActsByYear(Integer year) {
		List<UserGroupCostVO> allUserGroupCostVO = new ArrayList<UserGroupCostVO>();
		List<Activity> allacts = actsDAO.findAll();
		List<Activity> validateacts = new ArrayList<Activity>();

		// 获取所有以及被validate的活动
		for (int i = 0; i < allacts.size(); i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(allacts.get(i).getActDate());
			if (allacts.get(i).getState().equals(ActivitiesConstant.STATE_VALIDATE)
					&& year.equals(cal.get(Calendar.YEAR))) {
				validateacts.add(allacts.get(i));
			}
		}
		List<Userlogin> allUser = userloginDAO.findAll();
		List<Group> groups = groupDAO.findAll();

		// 获取所有用户，并且遍历所有用户活动参与情况
		for (Userlogin oneuser : allUser) {
			UserGroupCostVO oneUserGroupCostVO = new UserGroupCostVO();
			Integer userId = oneuser.getUserId();
			oneUserGroupCostVO.setUserId(userId);
			oneUserGroupCostVO.setUserName(oneuser.getUserName());
			List<GroupCostVO> allGroupCost = new ArrayList<GroupCostVO>();
			
			for (int j = 0; j < groups.size(); j++) {
				GroupCostVO onegroupCostVO = new GroupCostVO();
				onegroupCostVO.setGroupId(((Group) groups.get(j)).getGroupId());
				onegroupCostVO.setCost(0);

				allGroupCost.add(onegroupCostVO);
			}
			for (int j = 0; j < validateacts.size(); j++) {
				User_act oneuser_act = new User_act();
				Integer actId = validateacts.get(j).getActId();
				Integer groupId = validateacts.get(j).getGroupId();
				try {
					oneuser_act = user_actDAO.findByUserIdAndActId(userId,
							actId);
				} catch (Exception e) {
				}
				if (oneuser_act.getConsumption() != null) {
					for (GroupCostVO groupCostVO : allGroupCost) {
						if (groupId.equals(groupCostVO.getGroupId())) {
							float sum = (groupCostVO.getCost());
							groupCostVO.setCost(
									sum + oneuser_act.getConsumption());
						}
					}
				}
			}
			float sum = 0;
			for (int n = 0; n < allGroupCost.size(); n++) {
				sum += allGroupCost.get(n).getCost();
			}
			oneUserGroupCostVO.setSum(sum);
			oneUserGroupCostVO.setQuota(oneuser.getQuota());
			oneUserGroupCostVO
					.setDifferent(oneUserGroupCostVO.getQuota() - sum);
			oneUserGroupCostVO.setGroupCostVO(allGroupCost);
			allUserGroupCostVO.add(oneUserGroupCostVO);
		}

		return allUserGroupCostVO;
	}

	// 通过选择group的方式显示所有group这一年的活动参与情况的实现细节
	public List<GroupActVO> doGetAllActsByGroupId(Integer groupId) {
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
			List<User_act> useractPO = user_actDAO.findByActId(acts.get(i)
					.getActId());
			List<memberInVO> memberInVO = new ArrayList<memberInVO>();
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

	// 获取所有validate的活动的活动细节的具体实现
	public GroupActVO doGetAllValidateDetails(Integer actId) {
		Activity acts = actsDAO.findById(actId);
		GroupActVO actVO = new GroupActVO();

		actVO.setActId(acts.getActId());
		actVO.setActName(acts.getActName());
		actVO.setGroupId(acts.getGroupId());
		actVO.setActMoney(acts.getActMoney());
		actVO.setDescription(acts.getDescription());
		actVO.setState(acts.getState());

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String daterange = formatter.format(acts.getEnrollStartDate()) + " - "
				+ formatter.format(acts.getEnrollEndDate());
		actVO.setDaterange(daterange);
		actVO.setActDate(formatter.format(acts.getActDate()));
		actVO.setComment(acts.getComment());
		List<User_act> useractPO = user_actDAO.findByActId(acts.getActId());
		List<memberInVO> memberInVO = new ArrayList<memberInVO>();
		float sum = 0;
		Integer participatorNO = 0;
		for (int j = 0; j < useractPO.size(); j++) {
			memberInVO oneMemberIn = new memberInVO();
			oneMemberIn.setUserId(useractPO.get(j).getUserId());
			oneMemberIn.setParticipatorNO(useractPO.get(j).getParticipatorNO());
			oneMemberIn.setConsumption(useractPO.get(j).getConsumption());
			oneMemberIn.setRemark(useractPO.get(j).getRemark());
			sum += useractPO.get(j).getConsumption();
			participatorNO += useractPO.get(j).getParticipatorNO();
			Userlogin userPO = userloginDAO.findById(useractPO.get(j).getUserId());
			oneMemberIn.setUserName(userPO.getUserName());
			oneMemberIn.setUserDept(userPO.getUserDept());
			memberInVO.add(oneMemberIn);
		}
		actVO.setParticipatorNO(participatorNO);
		actVO.setMemberInVO(memberInVO);
		actVO.setSum(sum);

		return actVO;
	}

	// 获取所有需要被check和validate的活动
	public List<GroupActVO> doGetAllCheckValidateActs() {
		List<Activity> acts = actsDAO.findAll();
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
			actsVO.add(actsPO);

		}
		List<GroupActVO> InverseactsVO = new ArrayList<GroupActVO>();
		for (int j = 0; j < actsVO.size(); j++) {
			InverseactsVO.add(actsVO.get(actsVO.size() - j - 1));
		}
		return InverseactsVO;

	}
}
