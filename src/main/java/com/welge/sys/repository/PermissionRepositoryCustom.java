package com.welge.sys.repository;

import java.util.List;
import java.util.Map;

public interface PermissionRepositoryCustom {
	public List<Map<String, Object>> findByRoleId(String roleId);
}
