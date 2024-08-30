package com.miniproject.apigateway.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	
	private String token;
	private String id;
	private String fullName;
	private String email;
	private String phone;
	private String address;
	private String role;

}
