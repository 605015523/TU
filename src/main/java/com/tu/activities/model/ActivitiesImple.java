package com.tu.activities.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.activities.dao.Activity;
import com.tu.activities.dao.ActivityDAOInterface;

public class ActivitiesImple extends Observable implements ActivitiesInterface {

	private static final Log LOGGER = LogFactory.getLog(ActivitiesImple.class);
	private ActivityDAOInterface actsDAO = null;

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
	public List<ActivitiesVO> doGetAllActivity() {
		List<ActivitiesVO> activitiesVOs = new ArrayList<ActivitiesVO>();
		List<Activity> activitiesPOs = actsDAO.findAll();
		
		for (int i = 0; i < activitiesPOs.size(); i++) {
			Activity oneactivitiesPO = activitiesPOs.get(i);
			ActivitiesVO oneactivitiesVO = new ActivitiesVO();
			try {
				BeanUtils.copyProperties(oneactivitiesVO, oneactivitiesPO);
				activitiesVOs.add(oneactivitiesVO);
			} catch (IllegalAccessException e) {
				LOGGER.error("there is a IllegalAccessException: " + e.toString());
			} catch (InvocationTargetException e) {
				LOGGER.error("there is a InvocationTargetException: " + e.toString());
			}
		}
		return activitiesVOs;
	}

	// 通过actId获取一个活动
	@Override
	public ActivitiesVO doGetOneActById(Integer actId) {
		ActivitiesVO actVO = new ActivitiesVO();
		Activity actPO = actsDAO.findById(actId);
		try {
			BeanUtils.copyProperties(actVO, actPO);

		} catch (IllegalAccessException e) {
			LOGGER.error("there is a IllegalAccessException: " + e.toString());
		} catch (InvocationTargetException e) {
			LOGGER.error("there is a InvocationTargetException: " + e.toString());
		}
		return actVO;
	}

	// 添加一个活动
	@Override
	public Integer doAddOneAct(ActivitiesVO oneActivitiesVO) {
		Activity oneactsPO = new Activity();
		Integer actId = null;

		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneactsPO, oneActivitiesVO);
		} catch (IllegalAccessException e) {
			LOGGER.error("在MaterialImple类的doAddOneMaterial方法中利用BeanUtils类进行对象拷贝时出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			LOGGER.error("在MaterialImple类的doAddOneMaterial方法中利用BeanUtils类进行对象拷贝时出现了InvocationTargetException异常");
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
	public String doUpdateOneAct(ActivitiesVO oneActVO) {
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
	public String doDeleteOneActivities(ActivitiesVO oneActivitiesVO) {
		// TODO Auto-generated method stub
		return null;
	}

}
