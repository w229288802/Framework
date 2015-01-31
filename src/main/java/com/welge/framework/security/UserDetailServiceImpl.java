package com.welge.framework.security;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		ArrayList<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		ArrayList<GrantedAuthority> userlist = new ArrayList<GrantedAuthority>();
		userlist.add(new GrantedAuthorityImpl("ROLE_USER"));
		if(username.equals("admin"))
			return new User("admin", "21232f297a57a5a743894a0e4a801fc3", true, true, true, true,list);
		else{
			return new User(username, "21232f297a57a5a743894a0e4a801fc3", true, true, true, true,userlist);
		}
	}

}
