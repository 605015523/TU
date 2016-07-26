package com.tu.model.leaderview;

import java.util.*;

import com.tu.dao.activities.Activity;
import com.tu.dao.activities.ActivityDAOInterface;
import com.tu.mapper.DAOModelMapper;

public class LeaderviewImple extends Observable implements LeaderviewInterface {

	private ActivityDAOInterface actsDAO;
	private DAOModelMapper daoModelMapper;

	public LeaderviewImple() {
		// do nothing
	}

	public ActivityDAOInterface getActsDAO() {
		return this.actsDAO;
	}

	public void setActsDAO(ActivityDAOInterface actsDAO) {
		this.actsDAO = actsDAO;
	}

	// 通过groupId获取所有参加这个活动的user
	@Override
	public List<GroupActVO> doGetAllUserActsByGroupId(Integer groupId) {
		List<Activity> acts = actsDAO.findByGroupId(groupId);
		List<GroupActVO> actsVO = new ArrayList<GroupActVO>();

		for (Activity act : acts) {
			actsVO.add(daoModelMapper.convertActivityToGroupActVO(act));
		}
		Collections.reverse(actsVO);
		return actsVO;		
	}
	
	@Override
	public GroupActVO doGetUserActById(Integer actId) {
		Activity activity = actsDAO.findById(actId);
		return daoModelMapper.convertActivityToGroupActVO(activity);
	}

	public DAOModelMapper getDaoModelMapper() {
		return daoModelMapper;
	}

	public void setDaoModelMapper(DAOModelMapper daoModelMapper) {
		this.daoModelMapper = daoModelMapper;
	}
	
}
