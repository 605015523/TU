package com.tu.model.activities;

import java.util.List;

public interface ActivitiesInterface {

	public Integer doAddOneAct(ActivityVO oneActivitiesVO);

	public String doDeleteOneActivities(ActivityVO oneActivitiesVO);

	public ActivityVO doGetOneActById(Integer actId);

	public List<ActivityVO> doGetAllActivity();

	public String doUpdateOneAct(ActivityVO oneActVO);

}
