package com.tu.dao.messages;

import java.util.List;

public interface MessagesDAOInterface {

	Object save(Message oneActivitiesPO);

	List<Message> findAll();

	Message findById(Integer actId);

}
