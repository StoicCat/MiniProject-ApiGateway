package com.miniproject.apigateway.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseGateway {

	 private String token;
	    private String id;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phone;
	    private String address;
	    private String role;
}
