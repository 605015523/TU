package userlogin.dao;

import java.util.List;

public interface UserloginDAOInterface {

	public void save(Userlogin transientInstance);

	public void delete(Userlogin persistentInstance);

	public Userlogin findById(java.lang.Integer id);

	public List findByProperty(String propertyName, Object value);

	public List findAll();

	public Userlogin merge(Userlogin detachedInstance);

}