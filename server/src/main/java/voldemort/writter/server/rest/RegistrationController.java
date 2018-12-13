package voldemort.writter.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voldemort.writter.server.persistence.entity.User;
import voldemort.writter.server.service.registration.RegistrationService;

@RestController()
@RequestMapping("/rest/register")
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;

	@PutMapping()
	public ResponseEntity<String> register(@RequestBody User userDetails) {
		String token = registrationService.register(userDetails);
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

}
