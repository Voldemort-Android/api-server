package voldemort.writter.server.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import voldemort.writter.server.dto.security.AuthenticationDetails;
import voldemort.writter.server.exception.RestException;

public class AuthenticationHandlerInterceptor extends HandlerInterceptorAdapter {
	
	private TokenAuthenticationService tokenAuthenticationService;

	public AuthenticationHandlerInterceptor(TokenAuthenticationService tokenAuthenticationService) {
		this.tokenAuthenticationService = tokenAuthenticationService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws RestException {
		
		// Login and registration endpoints do not need security check.
		String requestUrl = request.getRequestURL().toString();
		if (requestUrl.contains("/rest/login") || requestUrl.contains("/rest/register")) {
			return true;
		}
		
		if (!(handler instanceof HandlerMethod)) {
			throw new RestException();
		}
		
		// Validate JWT.
		AuthenticationDetails details = tokenAuthenticationService.validateToken(request.getHeader("Authorization"));
		
		if (details == null) {
			return false;
		}
		
		// Set the authentication object in the security context.
		SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(details));
		
		return true;

	}
	
}
