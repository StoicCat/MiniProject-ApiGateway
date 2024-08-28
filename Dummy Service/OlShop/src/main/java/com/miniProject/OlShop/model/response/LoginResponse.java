package com.miniProject.OlShop.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
	
	private String token;
	private String id;
	private String fullName;
	private String email;
	private String phone;
	private String address;
	private String role;

}
