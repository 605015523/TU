package com.tu.activities.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tu.activities.dao.Activities;
import com.tu.activities.dao.ActivitiesDAOInterface;

public class ActivitiesImple extends Observable implements ActivitiesInterface {

	private static final Log LOG = LogFactory.getLog(ActivitiesImple.class);
	private ActivitiesDAOInterface actsDAO = null;

	public ActivitiesDAOInterface getActsDAO() {
		return this.actsDAO;
	}

	public void setActsDAO(ActivitiesDAOInterface actsDAO) {
		this.actsDAO = actsDAO;
	}

	public ActivitiesImple() {
		// 构造方法
	}

	// 获取所有活动
	public ArrayList<ActivitiesVO> doGetAllActivity() {
		ArrayList<ActivitiesVO> activitiesVOs = new ArrayList<ActivitiesVO>();
		ArrayList<Activities> activitiesPOs = new ArrayList<Activities>();
		activitiesPOs = (ArrayList) actsDAO.findAll();
		for (int i = 0; i < activitiesPOs.size(); i++) {
			Activities oneactivitiesPO = new Activities();
			oneactivitiesPO = activitiesPOs.get(i);
			ActivitiesVO oneactivitiesVO = new ActivitiesVO();
			try {
				BeanUtils.copyProperties(oneactivitiesVO, oneactivitiesPO);
				activitiesVOs.add(oneactivitiesVO);
			} catch (IllegalAccessException e) {
				LOG.error(" there is a IllegalAccessException");
			} catch (InvocationTargetException e) {
				LOG.error("there is a InvocationTargetException");
			}
		}
		return activitiesVOs;
	}

	// 通过actId获取一个活动
	public ActivitiesVO doGetOneActById(Integer actId) {
		ActivitiesVO actVO = new ActivitiesVO();
		Activities actPO = new Activities();
		actPO = actsDAO.findById(actId);
		try {
			BeanUtils.copyProperties(actVO, actPO);

		} catch (IllegalAccessException e) {
			LOG.error(" there is a IllegalAccessException");
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException");
		}
		return actVO;
	}

	// 添加一个活动
	public Integer doAddOneAct(ActivitiesVO oneActivitiesVO) {
		Activities oneactsPO = new Activities();
		Integer actId = null;
		String addMessage = null;

		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneactsPO, oneActivitiesVO);
		} catch (IllegalAccessException e) {
			LOG.error("在MaterialImple类的doAddOneMaterial方法中利用BeanUtils类进行对象拷贝时出现了IllegalAccessException异常");
		} catch (InvocationTargetException e) {
			LOG.error("在MaterialImple类的doAddOneMaterial方法中利用BeanUtils类进行对象拷贝时出现了InvocationTargetException异常");
		}
		try {
			actId = actsDAO.save(oneactsPO);
			addMessage = "save success";
		} catch (Exception e) {
			addMessage = e.toString();
		}
		return actId;
	}

	// 更新一个活动
	public String doUpdateOneAct(ActivitiesVO oneActVO) {
		Activities oneActPO = new Activities();
		Integer actId = oneActVO.getActId();
		oneActPO = actsDAO.findById(actId);
		String OkOrNot = null;
		try { // 利用Bean拷贝类实现简单地拷贝
			BeanUtils.copyProperties(oneActPO, oneActVO);
		} catch (IllegalAccessException e) {
			LOG.error("there is a IllegalAccessException while copy act in doUpdateOneact.");
		} catch (InvocationTargetException e) {
			LOG.error("there is a InvocationTargetException while copy act in doUpdateOneact.");
		}
		try {
			actsDAO.merge(oneActPO);
			OkOrNot = "merge success!";
		} catch (Exception e) {
			OkOrNot = e.toString();
		}

		return OkOrNot;
	}

	// 删除一个活动
	public String doDeleteOneActivities(ActivitiesVO oneActivitiesVO) {
		// TODO Auto-generated method stub
		return null;
	}

}
