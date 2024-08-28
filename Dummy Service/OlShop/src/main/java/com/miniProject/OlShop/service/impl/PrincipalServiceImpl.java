package com.miniProject.OlShop.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.miniProject.OlShop.service.PrincipalService;

@Service
public class PrincipalServiceImpl implements PrincipalService{

	@Override
	public String getUserId() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final Object userAuth = auth.getPrincipal();
		final String userId = userAuth.toString();
		
		return userId;
	}

	
}
