package com.tu.activities.model;

import java.util.List;

public interface ActivitiesInterface {

	public Integer doAddOneAct(ActivitiesVO oneActivitiesVO);

	public String doDeleteOneActivities(ActivitiesVO oneActivitiesVO);

	public ActivitiesVO doGetOneActById(Integer actId);

	public List<ActivitiesVO> doGetAllActivity();

	public String doUpdateOneAct(ActivitiesVO oneActVO);

}
