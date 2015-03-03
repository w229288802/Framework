package com.welge.framework.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import com.welge.framework.utils.SecurityUtils;

public class SecurityInterceptor extends AbstractSecurityInterceptor implements Filter{

	@Autowired
	private SecurityMetadataSource securityMetadataSource;
	
	
	public SecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			SecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		// TODO Auto-generated method stub
		return securityMetadataSource;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}
	private void invoke(FilterInvocation fi) throws ServletException, IOException {
		// object为FilterInvocation对象
		//1.获取请求资源的权限
		//执行Collection<ConfigAttribute> attributes = SecurityMetadataSource.getAttributes(object);
		//2.是否拥有权限
		//获取安全主体，可以强制转换为UserDetails的实例
		//1) UserDetails
		// Authentication authenticated = authenticateIfRequired();
		//this.accessDecisionManager.decide(authenticated, object, attributes);
		//用户拥有的权限
		//2) GrantedAuthority
		//Collection<GrantedAuthority> authenticated.getAuthorities()
		InterceptorStatusToken token = null;
		
		token = super.beforeInvocation(fi);
		if(SecurityUtils.getUsername().equals("anonymousUser")){
			throw new ServletException("anonymousUser");
		}
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
