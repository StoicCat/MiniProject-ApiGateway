package com.miniProject.OlShop.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

	private String address;
	
	private String email;
	
	private String role;
	
	private String fullName;
	
	private String phone;
	
	private String password;
}
