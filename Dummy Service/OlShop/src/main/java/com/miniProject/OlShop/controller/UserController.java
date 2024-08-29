package com.miniProject.OlShop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject.OlShop.model.request.CreateUserRequest;
import com.miniProject.OlShop.model.request.LoginRequest;
import com.miniProject.OlShop.model.response.CreateUserResponse;
import com.miniProject.OlShop.model.response.LoginResponse;
import com.miniProject.OlShop.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	
	@PostMapping("login-user")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		final Authentication auth = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		authenticationManager.authenticate(auth);
		final var response = userService.login(request);
		return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("add-user")
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){
		final var response = userService.add(request);
		return new ResponseEntity<CreateUserResponse>(response, HttpStatus.CREATED);
	}

}
