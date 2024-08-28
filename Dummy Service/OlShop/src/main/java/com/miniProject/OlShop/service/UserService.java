package com.miniProject.OlShop.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.miniProject.OlShop.entity.User;
import com.miniProject.OlShop.model.request.CreateUserRequest;
import com.miniProject.OlShop.model.request.LoginRequest;
import com.miniProject.OlShop.model.response.CreateUserResponse;
import com.miniProject.OlShop.model.response.LoginResponse;

public interface UserService extends UserDetailsService{
    
    LoginResponse login(LoginRequest request);
    
    CreateUserResponse add (CreateUserRequest request);

	Optional<User> getEntityById(String id);

}