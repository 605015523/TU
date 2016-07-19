package com.tu.userlogin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tu.userlogin.dao.Userlogin;
import com.tu.userlogin.dao.UserloginDAO;

public class MyUserDetailsService implements UserDetailsService {

	private UserloginDAO userLoginDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails res = null;
		Userlogin userLogin = userLoginDAO.findByUserName(username);
		
		if (userLogin != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(1);
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			
			res = new User(userLogin.getUserName(), 
					userLogin.getUserPassword(), true, 
	                true, true, true, authorities);
		}
		
		return res;
	}

	public void setUserLoginDAO(UserloginDAO userLoginDAO) {
		this.userLoginDAO = userLoginDAO;
	}

}
