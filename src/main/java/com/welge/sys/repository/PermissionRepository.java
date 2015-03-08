package com.welge.sys.repository;

import java.util.List;
import java.util.Map;

import com.welge.framework.dao.BaseRepository;
import com.welge.sys.entity.Permission;

public interface PermissionRepository extends BaseRepository<Permission, String>,PermissionRepositoryCustom{
	
}
