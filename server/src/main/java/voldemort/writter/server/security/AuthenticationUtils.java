package voldemort.writter.server.security;

import org.springframework.security.core.Authentication;

import voldemort.writter.server.dto.security.AuthenticationDetails;

public final class AuthenticationUtils {

	private AuthenticationUtils() {
		
	}
	
	public static AuthenticationDetails extractDetails(UserAuthentication authentication) {
		Object details = authentication.getDetails();
		if (details instanceof AuthenticationDetails) {
			return (AuthenticationDetails) details;
		}
		return null;
	}
	
	public static AuthenticationDetails extractDetails(Authentication authentication) {
		if (authentication instanceof UserAuthentication) {
			return extractDetails((UserAuthentication) authentication);
		}
		return null;
	}
	
}
