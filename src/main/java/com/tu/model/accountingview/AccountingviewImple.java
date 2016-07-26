package com.tu.model.accountingview;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.activities.Activity;
import com.tu.dao.activities.ActivityDAOInterface;
import com.tu.dao.user.act.UserAct;
import com.tu.dao.user.act.UserActDAOInterface;
import com.tu.dao.userlogin.Userlogin;
import com.tu.dao.userlogin.UserloginDAOInterface;
import com.tu.mapper.DAOModelMapper;
import com.tu.model.activities.ActivitiesConstant;
import com.tu.model.activities.ActivityVO;
import com.tu.model.leaderview.GroupActVO;

public class AccountingviewImple extends Observable implements
		AccountingviewInterface {
	
	private static final Log LOGGER = LogFactory.getLog(AccountingviewImple.class);

	private UserloginDAOInterface userloginDAO = null;
	private ActivityDAOInterface actsDAO = null;
	private UserActDAOInterface userActDAO = null;
	private DAOModelMapper daoModelMapper;

	public AccountingviewImple() {
		// nothing to do
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

	// 通过选择year的方式显示所有用户这一年的活动参与情况的实现细节
	@Override
	public List<UserGroupCostVO> doGetUserGroupCostsForValidatedActsByYear(Integer year) {
		List<UserGroupCostVO> allUserGroupCostVO = new ArrayList<UserGroupCostVO>();
		List<Activity> allacts = actsDAO.findByYear(year);
		List<Activity> validateacts = new ArrayList<Activity>();

		// 获取所有以及被validate的活动
		// Get all the validated activities
		Calendar cal = Calendar.getInstance();
		for (Activity activity : allacts) {
			cal.setTime(activity.getActDate());
			if (activity.getState().equals(ActivitiesConstant.STATE_VALIDATE)) {
				validateacts.add(activity);
			}
		}
		List<Userlogin> allUser = userloginDAO.findAll();

		// 获取所有用户，并且遍历所有用户活动参与情况
		// Get all the users and check their activities
		for (Userlogin oneuser : allUser) {
			UserGroupCostVO oneUserGroupCostVO = computeOneUserGroupCost(oneuser, validateacts);
			allUserGroupCostVO.add(oneUserGroupCostVO);
		}

		return allUserGroupCostVO;
	}
	
	/**
	 * Computes the user group costs for the given activities
	 * @param oneuser
	 * @param activities list of activities
	 * @return user group costs
	 */
	private UserGroupCostVO computeOneUserGroupCost(Userlogin oneuser, List<Activity> activities) {
		UserGroupCostVO oneUserGroupCostVO = new UserGroupCostVO();
		Integer userId = oneuser.getUserId();
		oneUserGroupCostVO.setUserId(userId);
		oneUserGroupCostVO.setUserName(oneuser.getUserName());
		Map<Integer, GroupCostVO> allGroupCost = new HashMap<Integer, GroupCostVO>();
		
		// Compute user group cost for the given activities
		for (Activity act : activities) {
			Integer actId = act.getActId();
			UserAct oneuserAct = userActDAO.findByUserIdAndActId(userId, actId);
			
			if (oneuserAct != null && oneuserAct.getConsumption() != null) {
				Integer groupId = act.getGroupId();
				GroupCostVO groupCostVO = allGroupCost.get(groupId);
				if (groupCostVO == null) {
					groupCostVO = new GroupCostVO();
					groupCostVO.setGroupId(groupId);
					groupCostVO.setCost(oneuserAct.getConsumption());
					allGroupCost.put(groupId, groupCostVO);
				} else {
					groupCostVO.setCost(groupCostVO.getCost() + oneuserAct.getConsumption());
				}
			}
		}
		
		// Sum of all costs
		float sum = 0;
		for (GroupCostVO groupCost : allGroupCost.values()) {
			sum += groupCost.getCost();
		}
		
		oneUserGroupCostVO.setSum(sum);
		oneUserGroupCostVO.setQuota(oneuser.getQuota());
		oneUserGroupCostVO.setDifferent(oneUserGroupCostVO.getQuota() - sum);
		oneUserGroupCostVO.setGroupCostVO(allGroupCost);
		
		return oneUserGroupCostVO;
	}

	// 通过选择group的方式显示所有group这一年的活动参与情况的实现细节
	@Override
	public List<GroupActVO> doGetAllActsByGroupId(Integer groupId) {
		List<Activity> acts = actsDAO.findByGroupId(groupId);
		List<GroupActVO> actsVO = new ArrayList<GroupActVO>();

		for (Activity activity : acts) {
			actsVO.add(daoModelMapper.convertActivityToGroupActVO(activity));
		}
		Collections.reverse(actsVO);

		return actsVO;
	}

	// 获取所有validate的活动的活动细节的具体实现
	@Override
	public GroupActVO doGetGroupActivityByID(Integer actId) {
		Activity activity = actsDAO.findById(actId);
		return daoModelMapper.convertActivityToGroupActVO(activity);
	}
	
	// 获取所有需要被check和validate的活动
	@Override
	public List<ActivityVO> doGetAllCheckValidateActs() {
		List<Activity> acts = actsDAO.findAll();
		List<ActivityVO> actsVO = new ArrayList<ActivityVO>();

		for (Activity activity : acts) {
			ActivityVO actVO = daoModelMapper.convertActivityToActivityVO(activity);
			actsVO.add(actVO);
		}
		
		Collections.reverse(actsVO);
		return actsVO;

	}

	public DAOModelMapper getDaoModelMapper() {
		return daoModelMapper;
	}

	public void setDaoModelMapper(DAOModelMapper daoModelMapper) {
		this.daoModelMapper = daoModelMapper;
	}
}
