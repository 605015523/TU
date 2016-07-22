package com.tu.model.accountingview;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.activities.Activity;
import com.tu.dao.activities.ActivityDAOInterface;
import com.tu.dao.group.Group;
import com.tu.dao.group.GroupDAOInterface;
import com.tu.dao.user.act.UserAct;
import com.tu.dao.user.act.UserActDAOInterface;
import com.tu.dao.userlogin.Userlogin;
import com.tu.dao.userlogin.UserloginDAOInterface;
import com.tu.model.activities.ActivitiesConstant;
import com.tu.model.leaderview.GroupActVO;
import com.tu.model.leaderview.MemberInVO;
import com.tu.util.ConfConstants;

public class AccountingviewImple extends Observable implements
		AccountingviewInterface {
	
	private static final Log LOGGER = LogFactory.getLog(AccountingviewImple.class);

	private UserloginDAOInterface userloginDAO = null;
	private ActivityDAOInterface actsDAO = null;
	private UserActDAOInterface userActDAO = null;
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

	public UserActDAOInterface getUserActDAO() {
		return this.userActDAO;
	}

	public void setUserActDAO(UserActDAOInterface userActDAO) {
		this.userActDAO = userActDAO;
	}

	public GroupDAOInterface getGroupDAO() {
		return this.groupDAO;
	}

	public void setGroupDAO(GroupDAOInterface groupDAO) {
		this.groupDAO = groupDAO;
	}

	// 通过选择year的方式显示所有用户这一年的活动参与情况的实现细节
	@Override
	public List<UserGroupCostVO> doGetAllActsByYear(Integer year) {
		List<UserGroupCostVO> allUserGroupCostVO = new ArrayList<UserGroupCostVO>();
		List<Activity> allacts = actsDAO.findAll();
		List<Activity> validateacts = new ArrayList<Activity>();

		// 获取所有以及被validate的活动
		// Get all the validated activities
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < allacts.size(); i++) {
			cal.setTime(allacts.get(i).getActDate());
			if (allacts.get(i).getState().equals(ActivitiesConstant.STATE_VALIDATE)
					&& year.equals(cal.get(Calendar.YEAR))) {
				validateacts.add(allacts.get(i));
			}
		}
		List<Userlogin> allUser = userloginDAO.findAll();
		List<Group> groups = groupDAO.findAll();

		// 获取所有用户，并且遍历所有用户活动参与情况
		// Get all the users and check their activities
		for (Userlogin oneuser : allUser) {
			UserGroupCostVO oneUserGroupCostVO = new UserGroupCostVO();
			Integer userId = oneuser.getUserId();
			oneUserGroupCostVO.setUserId(userId);
			oneUserGroupCostVO.setUserName(oneuser.getUserName());
			Map<Integer, GroupCostVO> allGroupCost = new HashMap<Integer, GroupCostVO>();
			
			// Initialize groups costs
			for (int j = 0; j < groups.size(); j++) {
				GroupCostVO onegroupCostVO = new GroupCostVO();
				onegroupCostVO.setGroupId(groups.get(j).getGroupId());
				onegroupCostVO.setCost(0);

				allGroupCost.put(onegroupCostVO.getGroupId(), onegroupCostVO);
			}
			
			for (int j = 0; j < validateacts.size(); j++) {
				Integer actId = validateacts.get(j).getActId();
				Integer groupId = validateacts.get(j).getGroupId();
				
				try {
					UserAct oneuserAct = userActDAO.findByUserIdAndActId(userId,
							actId);
					
					if (oneuserAct.getConsumption() != null) {
						GroupCostVO groupCostVO = allGroupCost.get(groupId);
						groupCostVO.setCost(groupCostVO.getCost() + oneuserAct.getConsumption());
					}
				} catch (Exception e) {
					LOGGER.error(e);
				}
			}
			float sum = 0;
			for (GroupCostVO groupCost : allGroupCost.values()) {
				sum += groupCost.getCost();
			}
			
			oneUserGroupCostVO.setSum(sum);
			oneUserGroupCostVO.setQuota(oneuser.getQuota());
			oneUserGroupCostVO
					.setDifferent(oneUserGroupCostVO.getQuota() - sum);
			oneUserGroupCostVO.setGroupCostVO(allGroupCost.values());
			
			allUserGroupCostVO.add(oneUserGroupCostVO);
		}

		return allUserGroupCostVO;
	}

	// 通过选择group的方式显示所有group这一年的活动参与情况的实现细节
	@Override
	public List<GroupActVO> doGetAllActsByGroupId(Integer groupId) {
		List<Activity> acts = actsDAO.findByGroupId(groupId);
		List<GroupActVO> actsVO = new ArrayList<GroupActVO>();

		for (Activity activity : acts) {
			actsVO.add(convertActivityToGroupActVO(activity));
		}
		Collections.reverse(actsVO);

		return actsVO;
	}

	// 获取所有validate的活动的活动细节的具体实现
	@Override
	public GroupActVO doGetAllValidateDetails(Integer actId) {
		Activity activity = actsDAO.findById(actId);
		return convertActivityToGroupActVO(activity);
	}
	
	private GroupActVO convertActivityToGroupActVO(Activity activity) {
		GroupActVO actsPO = new GroupActVO();
		actsPO.setActId(activity.getActId());
		actsPO.setActName(activity.getActName());
		actsPO.setGroupId(activity.getGroupId());
		actsPO.setActMoney(activity.getActMoney());
		actsPO.setDescription(activity.getDescription());
		actsPO.setState(activity.getState());

		SimpleDateFormat formatter = new SimpleDateFormat(ConfConstants.DATE_FORMAT);
		String daterange = formatter.format(activity.getEnrollStartDate())
				+ " - "	+ formatter.format(activity.getEnrollEndDate());
		actsPO.setDaterange(daterange);
		actsPO.setActDate(formatter.format(activity.getActDate()));
		actsPO.setComment(activity.getComment());
		List<UserAct> useractsPO = userActDAO.findByActId(activity.getActId());
		List<MemberInVO> memberInVO = new ArrayList<MemberInVO>();
		float sum = 0;
		Integer nbParticipants = 0;
		for (UserAct userActPO : useractsPO) {
			MemberInVO oneMemberIn = new MemberInVO();
			oneMemberIn.setUserId(userActPO.getUserId());
			oneMemberIn.setNbParticipants(userActPO.getNbParticipants());
			oneMemberIn.setConsumption(userActPO.getConsumption());
			oneMemberIn.setRemark(userActPO.getRemark());
			sum += userActPO.getConsumption();
			nbParticipants += userActPO.getNbParticipants();
			Userlogin userPO = userloginDAO.findById(userActPO.getUserId());
			oneMemberIn.setUserName(userPO.getUserName());
			oneMemberIn.setUserDept(userPO.getUserDept());
			memberInVO.add(oneMemberIn);
		}
		actsPO.setNbParticipants(nbParticipants);
		actsPO.setMemberInVO(memberInVO);
		actsPO.setSum(sum);
		
		return actsPO;
	}

	// 获取所有需要被check和validate的活动
	@Override
	public List<GroupActVO> doGetAllCheckValidateActs() {
		List<Activity> acts = actsDAO.findAll();
		List<GroupActVO> actsVO = new ArrayList<GroupActVO>();

		for (Activity activity : acts) {
			GroupActVO actsPO = new GroupActVO();
			actsPO.setActId(activity.getActId());
			actsPO.setActName(activity.getActName());
			actsPO.setGroupId(activity.getGroupId());
			actsPO.setActMoney(activity.getActMoney());
			actsPO.setDescription(activity.getDescription());
			actsPO.setState(activity.getState());
			SimpleDateFormat formatter = new SimpleDateFormat(ConfConstants.DATE_FORMAT);
			String daterange = formatter.format(activity
					.getEnrollStartDate())
					+ " - "
					+ formatter.format(activity.getEnrollEndDate());
			actsPO.setDaterange(daterange);
			actsPO.setActDate(formatter.format(activity.getActDate()));
			actsVO.add(actsPO);

		}
		
		Collections.reverse(actsVO);
		return actsVO;

	}
}
