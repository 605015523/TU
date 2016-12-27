package com.tu.model.activities;

import java.util.List;

public interface ActivitiesInterface {

	Integer doAddOneAct(ActivityVO oneActivitiesVO);

	String doDeleteOneActivities(ActivityVO oneActivitiesVO);

	ActivityVO doGetOneActById(Integer actId);

	List<ActivityVO> doGetAllActivity();
	
	List<ActivityVO> doGetUpcomingActivity();

	String doUpdateOneAct(ActivityVO oneActVO);

}
