package com.welge.framework.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public final class SecurityUtils {
	
	private SecurityUtils() {
		
	}
	
	/**
	 * 获取当前用户的ID
	 * @return
	 */
	
	public static Long getUserId() {
		//return  getCurrentUser().;
		return null;
	}
	/**
	 * 获取当前的用户名
	 * @return
	 */
	public static String getUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends User> T getCurrentUser() {
		Authentication authentication = getAuthentication();

		if (authentication == null) {
			return null;
		}

		Object principal = authentication.getPrincipal();
		if (!(principal instanceof User)) {
			return null;
		}

		return (T) principal;
	}
	/**
	 * 取得Authentication, 如当前SecurityContext为空时返回null.
	 */
	private static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return null;
		}

		return context.getAuthentication();
	}


}
