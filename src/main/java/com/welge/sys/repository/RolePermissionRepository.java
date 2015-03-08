package com.welge.sys.repository;

import java.util.Collection;
import java.util.List;

import com.welge.framework.dao.BaseRepository;
import com.welge.sys.entity.RolePermission;

public interface RolePermissionRepository extends BaseRepository<RolePermission, String>{
	public List<RolePermission> findByRoleIdAndPermissionIdIn(String id,Collection<?> list);
}
