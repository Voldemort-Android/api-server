package voldemort.writter.server.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voldemort.writter.server.service.login.LoginService;

@RestController()
@RequestMapping("/rest/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping()
	public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
		String token = loginService.login(credentials);
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

}
