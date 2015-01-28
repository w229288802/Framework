package com.welge.framework.auth;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		ArrayList<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		return new User("admin", "21232f297a57a5a743894a0e4a801fc3", true, true, true, true,list);
	}

}
