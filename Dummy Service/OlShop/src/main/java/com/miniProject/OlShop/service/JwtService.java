package com.miniProject.OlShop.service;

import java.util.Map;

public interface JwtService {

	String generateJwt(final Map<String, Object> claims);
	
	Map<String, Object> parseJwt(final String jwt);
}
