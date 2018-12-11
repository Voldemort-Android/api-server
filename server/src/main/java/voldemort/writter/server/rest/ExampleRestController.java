package voldemort.writter.server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voldemort.writter.server.dto.security.AuthenticationDetails;
import voldemort.writter.server.security.AuthenticationUtils;

@RestController()
@RequestMapping("/rest/example")
public class ExampleRestController {
	
	@GetMapping()
	public ResponseEntity<String> doesThisWork() {
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}
	
	@GetMapping("/{param}")
	public ResponseEntity<String> doesThisWork(@PathVariable String param) {
		return new ResponseEntity<>("Param was '" + param + "'", HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Object> doesThisWork2(@RequestBody String data) {
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("/whoami")
	public ResponseEntity<Object> doesThisWork3(Authentication authentication) {
		AuthenticationDetails userDetails = AuthenticationUtils.extractDetails(authentication);
		if (userDetails != null) {
			return new ResponseEntity<>(userDetails, HttpStatus.OK);
		}
		return new ResponseEntity<>("I don't know you", HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
