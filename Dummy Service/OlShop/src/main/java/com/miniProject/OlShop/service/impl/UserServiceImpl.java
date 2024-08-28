package com.miniProject.OlShop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.miniProject.OlShop.entity.User;
import com.miniProject.OlShop.model.request.CreateUserRequest;
import com.miniProject.OlShop.model.request.LoginRequest;
import com.miniProject.OlShop.model.response.CreateUserResponse;
import com.miniProject.OlShop.model.response.LoginResponse;
import com.miniProject.OlShop.repository.UserRepository;
import com.miniProject.OlShop.service.JwtService;
import com.miniProject.OlShop.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final JwtService jwtService;
	
	private final PasswordEncoder passowordEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final var user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			final User result = user.get();
			return new org.springframework.security.core.userdetails.User(result.getEmail(), result.getPassword(),
					new ArrayList<>());
		}
		throw new UsernameNotFoundException("Invalid Login");
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		final var email = request.getEmail();
		final var result = userRepository.findByEmail(email);
		final var user = result.get();
		final var response = new LoginResponse();
		Map<String, Object> claim = new HashMap<>();
		claim.put("id", user.getId());
		final var token = jwtService.generateJwt(claim);
		

		response.setId(user.getId().toString());
		response.setFullName(user.getFullName());
		response.setEmail(user.getEmail());
		response.setRole(user.getRole());
		response.setAddress(user.getAddress());
		response.setPhone(user.getPhoneNo());

		response.setToken(token);

		return response;
	}

	@Override
	public CreateUserResponse add(CreateUserRequest request) {
		
		final var user = new User();
		
		final var email = request.getEmail();
		final var fullName = request.getFullName();
		final var password = request.getPassword();
		final var phone = request.getPhone();
		final var role = request.getRole();
		final var address = request.getAddress();
		
		final var encodedPassword = passowordEncoder.encode(password);
		
		final var createdBy = "system";
		
		user.setEmail(email);
		user.setFullName(fullName);
		user.setPassword(encodedPassword);
		user.setPhoneNo(phone);
		user.setRole(role);
		user.setAddress(address);
		user.setCreatedBy(createdBy);
		
		userRepository.save(user);
		
		final var response = new CreateUserResponse();
		response.setMessage("User " + fullName + " berhasil terbuat");
		
		return response;
	}

}
