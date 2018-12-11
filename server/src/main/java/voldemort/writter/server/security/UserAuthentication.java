package voldemort.writter.server.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import voldemort.writter.server.dto.security.AuthenticationDetails;

public class UserAuthentication implements Authentication {
	
	private static final long serialVersionUID = 1L;
	
	private AuthenticationDetails details;
	
	public UserAuthentication(AuthenticationDetails details) {
		this.details = details;
	}

	@Override
	public String getName() {
		return details.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		return details;
	}

	@Override
	public Object getPrincipal() {
		return details.getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
	}

}
