package com.welge.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_role_permission")
public class RolePermission {
	@Id
	private String id;
	@Column(name="role_id")
	private String roleId;
	@Column(name="permission_id")
	private String permissionId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
}
