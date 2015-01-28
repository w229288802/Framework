package com.welge.framework.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.welge.framework.model.SysUser;
import com.welge.framework.repository.IUserDao;

@Service
@Transactional
public class UserService {
	@Autowired
	private IUserDao userDao;
	public void save(SysUser user){
		userDao.save(user);
	}
	public SysUser findById(String id){
		SysUser user = new SysUser();
		user.setId(UUID.randomUUID().toString());
		user.setName("aaa");
		userDao.save(user);
		//int i=1/0;
		return userDao.findById(id);
	}
}
