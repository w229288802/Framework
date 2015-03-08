package com.welge.sys.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.welge.framework.dao.CommonDao;
import com.welge.sys.repository.PermissionRepositoryCustom;

public class PermissionRepositoryImpl implements PermissionRepositoryCustom{
	@Autowired
	private CommonDao commonDao;
	public List<Map<String, Object>> findByRoleId(String roleId){
		StringBuilder sb = new StringBuilder();
		sb.append("select p.id,p.name,p.type ,p.code,p.pid,p.root,p.description,p.url,p.pid ,IF(rp.ROLE_ID,true,false) as checked_ from sys_permission p"); 
		sb.append(" LEFT OUTER JOIN sys_role_permission rp on rp.PERMISSION_ID = p.ID and rp.ROLE_ID=?");
		System.out.println(sb.toString());
		return commonDao.findBySql(sb.toString(), roleId);
	}
}
