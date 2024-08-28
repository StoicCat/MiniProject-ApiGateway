package com.miniProject.OlShop.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.miniProject.OlShop.model.request.LoginRequest;
import com.miniProject.OlShop.model.response.LoginResponse;

public interface UserService extends UserDetailsService{
	
	LoginResponse login(LoginRequest request);

}
