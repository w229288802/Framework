package com.welge.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.welge.framework.dao.BaseRepository;
import com.welge.framework.service.BaseService;
import com.welge.sys.entity.User;
import com.welge.sys.repository.UserRepository;
@Service
@Transactional

public class UserService extends BaseService<User, String>{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public BaseRepository<User, String> getBaseRepository() {
		return userRepository;
	}

}
