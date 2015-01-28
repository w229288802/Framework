package com.welge.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;

public class SecurityFilter extends AbstractSecurityInterceptor implements Filter{
	
	@Override
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return null;
	}

	@Override
	public void destroy() {
		Logger.getLogger(this.getClass()).debug("destory method invoked");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			FilterInvocation filterInvocation = new FilterInvocation(request, response, chain);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		Logger.getLogger(this.getClass()).debug("init method invoked");
	}

}
