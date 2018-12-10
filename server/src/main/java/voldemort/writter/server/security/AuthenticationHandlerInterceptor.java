package voldemort.writter.server.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import voldemort.writter.server.exception.GenericException;

public class AuthenticationHandlerInterceptor extends HandlerInterceptorAdapter {
	
	private TokenAuthenticationService tokenAuthenticationService;

	public AuthenticationHandlerInterceptor(TokenAuthenticationService tokenAuthenticationService) {
		this.tokenAuthenticationService = tokenAuthenticationService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		// Login endpoint does not need security check.
		if (request.getRequestURL().toString().endsWith("login")) {
			return true;
		}
		
		if (!(handler instanceof HandlerMethod)) {
			throw new GenericException("Internal server error");
		}
		
		
		// Validate JWT.
		return tokenAuthenticationService.validateToken(request.getHeader("Authorization"));
		
	}
	
}
