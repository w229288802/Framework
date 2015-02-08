package com.welge.framework.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class SecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource{
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if(resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		//	List<Resources> resources = this.resourcesDao.findAll();
		//	for (Resources resource : resources) {
				Collection<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
				//以权限名封装为Spring的security Object
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_USER");
				configAttributes.add(configAttribute);
				resourceMap.put("/sys/role.jsp", configAttributes);
				
				//resourceMap.put("/sys/user.jsp", configAttributes2);
		}   
		LogFactory.getLog(this.getClass()).info(resourceMap.get(requestUrl));
	//	if(resourceMap.get(requestUrl)==null)return new HashSet<ConfigAttribute>();
		return resourceMap.get(requestUrl);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
