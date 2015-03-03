package com.welge.framework.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

@Service
public class CommonService {
	@PersistenceContext
	private EntityManager em ;
	public String getFieldValue(){
		/*Query createQuery = em.createQuery("select u from User u ");
		List<User> resultList = createQuery.getResultList(); 
		HashMap hashMap = new HashMap();
		hashMap.put("a", resultList);
		hashMap.put("b", "b");
		_PrintUtils.println(hashMap,3);*/
		return null ;
	}
}
