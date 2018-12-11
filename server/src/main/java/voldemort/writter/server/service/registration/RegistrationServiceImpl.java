package voldemort.writter.server.service.registration;

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
public class RegistrationServiceImpl implements RegistrationService {
	
	// TODO Move these to a constants file.
	private final int minUsernameLength = 4;
	private final int minPasswordLength = 2;
	
	@Autowired
	TokenAuthenticationService tokenAuthenticationService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public String register(User userDetails) {
		
		String username = userDetails.getUsername();
		String password = userDetails.getPassword();
		String email = userDetails.getEmail();
		String firstName = userDetails.getFirstName();
		String lastName = userDetails.getLastName();
		
		if (usernameIsValid(username) && 
				passwordIsValid(password) && 
				emailIsValid(email) &&
				nameIsValid(firstName) &&
				nameIsValid(lastName)) {
			
			User user = new User();
			user.setUsername(username.toLowerCase());
			user.setPassword(passwordEncoder.encode(password));
			user.setEmail(email.toLowerCase());
			user.setFirstName(formatName(firstName));
			user.setLastName(formatName(lastName));
			
			// TODO Save the user to the database.
			userDao.createUser(user);
			
			return tokenAuthenticationService.generateToken(user);
		}
		
		return null;
	}
	
	private boolean usernameIsValid(String username) {
		
		if (StringUtils.isEmpty(username)) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Username is blank or missing");
		}
		
		if (username.length() < minUsernameLength) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Username is too short");
		}
		
		if (!username.matches("^[a-zA-Z0-9]*$")) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Username cannot contain spaces or special characters");
		}
		
		if (userDao.findByUsername(username) != null) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Username already exists");
		}
		
		return true;
		
	}
	
	private boolean passwordIsValid(String password) {
		
		if (StringUtils.isEmpty(password)) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Password is blank or missing");
		}
		
		if (password.length() < minPasswordLength) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Password is too short");
		}
		
		return true;
		
	}
	
	private boolean emailIsValid(String email) {

		if (StringUtils.isEmpty(email)) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Email address is blank or missing");
		}
		
		// Regex source: https://stackoverflow.com/a/44674038
		if (!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Invalid email address");
		}
		
		if (userDao.findByEmail(email) != null) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Email already exists");
		}
		
		return true;
		
	}
	
	private boolean nameIsValid(String name) {
		
		if (StringUtils.isEmpty(name)) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Your name is blank or missing");
		}
		
		if (!name.matches("^[a-zA-Z]*$")) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Your name can only contain letters");
		}
		
		return true;
	}
	
	private String formatName(String name) {
		name = name.toUpperCase();
		return name.charAt(0) + name.substring(1).toLowerCase();
	}
	
}
