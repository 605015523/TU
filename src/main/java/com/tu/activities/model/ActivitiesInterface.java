package com.tu.activities.model;

import java.util.ArrayList;

public interface ActivitiesInterface {

	public Integer doAddOneAct(ActivitiesVO oneActivitiesVO);

	public String doDeleteOneActivities(ActivitiesVO oneActivitiesVO);

	public ActivitiesVO doGetOneActById(Integer actId);

	public ArrayList<ActivitiesVO> doGetAllActivity();

	public String doUpdateOneAct(ActivitiesVO oneActVO);

}
