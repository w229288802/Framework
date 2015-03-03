package com.welge.framework.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.welge.framework.dao.BaseRepository;

public abstract class BaseService<T,ID extends Serializable> {
	public abstract BaseRepository<T, ID> getBaseRepository();
	public Page<T> findAll(Pageable pageable){
		return getBaseRepository().findAll(pageable);
	}
	public List<T> getListAll(Pageable pageable){
		return getBaseRepository().findAll(pageable).getContent();
	}
	
	public List<T> getListAll(){
		return getBaseRepository().findAll();
	}
}
