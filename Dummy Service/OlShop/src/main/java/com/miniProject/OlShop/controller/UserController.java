package com.miniProject.OlShop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.miniProject.OlShop.model.request.CreateUserRequest;
import com.miniProject.OlShop.model.request.LoginRequest;
import com.miniProject.OlShop.model.response.CreateUserResponse;
import com.miniProject.OlShop.model.response.LoginResponse;
import com.miniProject.OlShop.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Api(tags = "User Management")
public class UserController {
	
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	
	@PostMapping("login-user")
	@ApiOperation(value = "Authenticate and login a user")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		final Authentication auth = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		authenticationManager.authenticate(auth);
		final var response = userService.login(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("add-user")
	@ApiOperation(value = "Register a new user")
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){
		final var response = userService.add(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
