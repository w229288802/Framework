package com.welge.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.welge.framework.dao.BaseRepository;
import com.welge.framework.service.BaseService;
import com.welge.sys.entity.Role;
import com.welge.sys.repository.RoleRepository;

@Service
@Transactional
public class RoleService extends BaseService<Role, String>{
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public BaseRepository<Role, String> getBaseRepository() {
		return roleRepository;
	}

}
