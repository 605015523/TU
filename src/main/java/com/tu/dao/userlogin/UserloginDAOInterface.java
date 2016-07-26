package com.tu.dao.userlogin;

import java.util.List;

public interface UserloginDAOInterface {

	void save(Userlogin transientInstance);

	void delete(Userlogin persistentInstance);

	Userlogin findById(java.lang.Integer id);

	List<Userlogin> findByProperty(String propertyName, Object value);
	
	Userlogin findByUserName(String userName);

	List<Userlogin> findAll();

	Userlogin merge(Userlogin detachedInstance);

}