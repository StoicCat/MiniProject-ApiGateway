package com.miniProject.OlShop.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.miniProject.OlShop.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private List<RequestMatcher> matchers;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Bypass JWT authentication for Swagger endpoints
		if (request.getRequestURI().startsWith("/swagger-ui/") || request.getRequestURI().startsWith("/v3/api-docs/")) {
			filterChain.doFilter(request, response);
			return;
		}

		final String header = request.getHeader("Authorization");
		if (header != null) {
			final String jwt = header.replaceFirst("Bearer ", "");
			try {
				final Map<String, Object> map = jwtService.parseJwt(jwt);

				final Authentication auth = new UsernamePasswordAuthenticationToken(map.get("id"), null);
				SecurityContextHolder.getContext().setAuthentication(auth);

			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
				return;
			}
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
			return;
		}

		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		
		return matchers.stream().anyMatch(m -> m.matches(request)) || request.getRequestURI().startsWith("/swagger-ui/")
				|| request.getRequestURI().startsWith("/v3/api-docs/");
	}

}
