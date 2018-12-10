package voldemort.writter.server.service.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.base.Objects;

import voldemort.writter.server.exception.RestException;
import voldemort.writter.server.persistence.entity.User;
import voldemort.writter.server.security.TokenAuthenticationService;

@Service
public class LoginServiceImpl implements LoginService {
	
	private final Map<String, String> dummyAccounts;
	
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;	
	
	public LoginServiceImpl() {
		dummyAccounts = new HashMap<>();
		dummyAccounts.put("asdf", "asdf");
		dummyAccounts.put("hi", "hello");
	}
	
	public String login(Map<String, String> credentials) {
		
		String username = credentials.get("username");
		String password = credentials.get("password");
		
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Missing username and/or password");
		}
			
		if (Objects.equal(dummyAccounts.get(username), password)) {
			
			User dummyUser = new User();
			dummyUser.setId(0L);
			dummyUser.setUsername(username);
			dummyUser.setEmail("hello@world.com");
			
			return tokenAuthenticationService.generateToken(dummyUser);
		}
		
		throw new RestException(HttpStatus.UNAUTHORIZED, "Invalid username or password");	
	}

}
