package com.welge.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welge.framework.model.SysUser;

public interface IUserDao extends JpaRepository<SysUser, String>{
	SysUser findById(String id);
}
