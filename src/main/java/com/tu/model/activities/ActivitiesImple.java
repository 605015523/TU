package com.tu.model.activities;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.dao.activities.Activity;
import com.tu.dao.activities.ActivityDAOInterface;
import com.tu.mapper.DAOModelMapper;

public class ActivitiesImple extends Observable implements ActivitiesInterface {

	private static final Log LOGGER = LogFactory.getLog(ActivitiesImple.class);
	private ActivityDAOInterface actsDAO = null;
	private DAOModelMapper daoModelMapper;

	public ActivitiesImple() {
		// 构造方法
	}
	
	public ActivityDAOInterface getActsDAO() {
		return this.actsDAO;
	}

	public void setActsDAO(ActivityDAOInterface actsDAO) {
		this.actsDAO = actsDAO;
	}
	

	// 获取所有活动
	@Override
	public List<ActivityVO> doGetAllActivity() {
		List<ActivityVO> activitiesVOs = new ArrayList<ActivityVO>();
		List<Activity> activitiesPOs = actsDAO.findAll();
		
		for (Activity oneactivitiesPO : activitiesPOs) {
			activitiesVOs.add(daoModelMapper.convertActivityToActivityVO(oneactivitiesPO));
		}
		return activitiesVOs;
	}

	// 通过actId获取一个活动
	@Override
	public ActivityVO doGetOneActById(Integer actId) {
		Activity actPO = actsDAO.findById(actId);
		return daoModelMapper.convertActivityToActivityVO(actPO);
	}
	
	// 添加一个活动
	@Override
	public Integer doAddOneAct(ActivityVO oneActivitiesVO) {
		Activity oneactsPO = new Activity();
		Integer actId = null;

		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneactsPO, oneActivitiesVO);
		} catch (IllegalAccessException e) {
			LOGGER.error(e);
		} catch (InvocationTargetException e) {
			LOGGER.error(e);
		}
		try {
			actId = actsDAO.save(oneactsPO);
			LOGGER.debug("save success");
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return actId;
	}

	// 更新一个活动
	@Override
	public String doUpdateOneAct(ActivityVO oneActVO) {
		Integer actId = oneActVO.getActId();
		Activity oneActPO = actsDAO.findById(actId);
		String okOrNot = null;
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneActPO, oneActVO);
		} catch (IllegalAccessException e) {
			LOGGER.error("there is a IllegalAccessException while copy act in doUpdateOneact.");
		} catch (InvocationTargetException e) {
			LOGGER.error("there is a InvocationTargetException while copy act in doUpdateOneact.");
		}
		try {
			actsDAO.merge(oneActPO);
			okOrNot = "merge success!";
		} catch (Exception e) {
			okOrNot = e.toString();
		}

		return okOrNot;
	}

	// 删除一个活动
	@Override
	public String doDeleteOneActivities(ActivityVO oneActivitiesVO) {
		// TODO Auto-generated method stub
		return null;
	}

	public DAOModelMapper getDaoModelMapper() {
		return daoModelMapper;
	}

	public void setDaoModelMapper(DAOModelMapper daoModelMapper) {
		this.daoModelMapper = daoModelMapper;
	}

}
