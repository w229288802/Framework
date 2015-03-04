package com.welge.framework.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.welge.framework.dao.BaseRepository;

public abstract class BaseService<T,ID extends Serializable> {
	public abstract BaseRepository<T, ID> getBaseRepository();
	
	public void delete(ID id){
		getBaseRepository().delete(id);
	}
	public void delete(Collection<T> collection){
		getBaseRepository().delete(collection);
	}
	
	public T getOne(ID id){
		return getBaseRepository().findOne(id);
	}
	
	public Page<T> getListPage(Pageable pageable){
		return getBaseRepository().findAll(pageable);
	}
	
	public List<T> getListAll(){
		return getBaseRepository().findAll();
	}
	public void save(Collection<T> collection){
		getBaseRepository().save(collection);
	}
	public void save(T entity){
		getBaseRepository().save(entity);
	}
}
