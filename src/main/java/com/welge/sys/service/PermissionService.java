package com.welge.sys.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.welge.framework.dao.BaseRepository;
import com.welge.framework.service.BaseService;
import com.welge.framework.utils.DBUtils;
import com.welge.framework.utils._PrintUtils;
import com.welge.sys.entity.Permission;
import com.welge.sys.entity.RolePermission;
import com.welge.sys.repository.PermissionRepository;
import com.welge.sys.repository.RolePermissionRepository;
/**
 * @author haha
 *
 */
@Service
@Transactional
public class PermissionService extends BaseService<Permission,String>{

	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired 
	private RolePermissionRepository rolePermissionRepository;
	@Override
	public BaseRepository<Permission, String> getBaseRepository() {
		return permissionRepository;
	}
	
	public List<Map<String, Object>> getListByRoleId(String roleId) {
		return permissionRepository.findByRoleId(roleId);
	}

	public void grantPermission(String roleId,String[] pIds,String[] pIds_){
		if(pIds!=null){
			//得到不存在的list
			List<RolePermission> exist = rolePermissionRepository.findByRoleIdAndPermissionIdIn(roleId,Arrays.asList(pIds));
			LinkedList<String> newList = new LinkedList<String>(Arrays.asList(pIds));
			for(RolePermission rp :exist){
				newList.remove(rp.getPermissionId());
			}
			//保存不存在的list
			RolePermission rolePermission = null;
			ArrayList<RolePermission> list = new ArrayList<RolePermission>();
			for(String pid:newList){
				rolePermission = new RolePermission();
				rolePermission.setId(DBUtils.generateBeanID());
				rolePermission.setPermissionId(pid);
				rolePermission.setRoleId(roleId);
				list.add(rolePermission);
			}
			rolePermissionRepository.save(list);
		}
		if(pIds_!=null){
			//删除已存在的list
			List<RolePermission> oldList = rolePermissionRepository.findByRoleIdAndPermissionIdIn(roleId,Arrays.asList(pIds_));
			_PrintUtils.println(oldList);
			rolePermissionRepository.delete(oldList);
		}
	}
}
