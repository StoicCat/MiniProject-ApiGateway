package com.miniproject.apigateway.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestGateway {

	private String address;
	
	private String email;
	
	private String role;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private String password;
}
