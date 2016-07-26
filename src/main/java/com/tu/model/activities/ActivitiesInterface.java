package com.tu.model.activities;

import java.util.List;

public interface ActivitiesInterface {

	Integer doAddOneAct(ActivityVO oneActivitiesVO);

	String doDeleteOneActivities(ActivityVO oneActivitiesVO);

	ActivityVO doGetOneActById(Integer actId);

	List<ActivityVO> doGetAllActivity();

	String doUpdateOneAct(ActivityVO oneActVO);

}
