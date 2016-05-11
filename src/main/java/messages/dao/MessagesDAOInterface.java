package messages.dao;

import java.util.List;

public interface MessagesDAOInterface {

	public Object save(Messages oneActivitiesPO);

	public List findAll();

	public Messages findById(Integer actId);

}
