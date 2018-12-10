package voldemort.writter.server.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Objects;

import voldemort.writter.server.security.TokenAuthenticationService;

@RestController()
@RequestMapping("/rest/login")
public class LoginController {
	
	private final Map<String, String> dummyAccounts;
	
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;	
	
	public LoginController() {
		dummyAccounts = new HashMap<>();
		dummyAccounts.put("asdf", "asdf");
		dummyAccounts.put("hi", "hello");
	}
	
	@PostMapping()
	public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
		
		String username = credentials.get("username");
		String password = credentials.get("password");
		
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return new ResponseEntity<String>("Missing username and/or password.", HttpStatus.BAD_REQUEST);
		}
			
		if (Objects.equal(dummyAccounts.get(username), password)) {
			String token = tokenAuthenticationService.generateToken(username, password);
			return new ResponseEntity<String>(token, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Invalid username or password", HttpStatus.UNAUTHORIZED);
		
	}

}
