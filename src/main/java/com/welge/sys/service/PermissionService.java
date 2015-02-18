package com.welge.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.welge.framework.dao.BaseRepository;
import com.welge.framework.service.BaseService;
import com.welge.sys.entity.Permission;
import com.welge.sys.repository.PermissionRepository;
@Service
@Transactional
public class PermissionService extends BaseService<Permission,String>{

	@Autowired
	private PermissionRepository permissionRepository;
	@Override
	public BaseRepository<Permission, String> getBaseRepository() {
		return permissionRepository;
	}
}
