package com.welge.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.welge.framework.dao.BaseRepository;
import com.welge.framework.service.BaseService;
import com.welge.sys.entity.Department;
import com.welge.sys.repository.DepartmentRepository;
@Service
@Transactional
public class DepartmentService extends BaseService<Department, String>{
	@Autowired
	private DepartmentRepository departmentRepository;
	@Override
	public BaseRepository<Department, String> getBaseRepository() {
		return departmentRepository;
	}

}
