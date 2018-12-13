package voldemort.writter.server.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import voldemort.writter.server.dto.security.AuthenticationDetails;
import voldemort.writter.server.persistence.entity.User;

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
	
	public static AuthenticationDetails getDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return extractDetails(authentication);
	}
	
	/**
	 * Returns a User object with only the ID field populated
	 * for use with database queries.
	 */
	public static User getCurrentUser() {
		AuthenticationDetails userDetails = getDetails();
		return new User(userDetails.getId());
	}
	
}
