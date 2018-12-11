package voldemort.writter.server.service.login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import voldemort.writter.server.exception.RestException;
import voldemort.writter.server.persistence.dao.UserDao;
import voldemort.writter.server.persistence.entity.User;
import voldemort.writter.server.security.TokenAuthenticationService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDao userDao;
	
	public String login(Map<String, String> credentials) {
		
		String username = credentials.get("username");
		String password = credentials.get("password");
		
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Missing username and/or password");
		}
			
		User existingUser = userDao.findByUsername(username);
		if (existingUser != null && passwordEncoder.matches(password, existingUser.getPassword())) {
			return tokenAuthenticationService.generateToken(existingUser);
		}
		
		throw new RestException(HttpStatus.UNAUTHORIZED, "Invalid username or password");	
	}

}
