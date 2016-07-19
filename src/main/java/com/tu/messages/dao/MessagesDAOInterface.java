package com.tu.messages.dao;

import java.util.List;

public interface MessagesDAOInterface {

	public Object save(Messages oneActivitiesPO);

	public List<Messages> findAll();

	public Messages findById(Integer actId);

}
