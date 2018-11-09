package server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/rest/example")
public class ExampleRestController {
	
	@GetMapping()
	public ResponseEntity<String> doesThisWork() {
		return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
	}
	
	@GetMapping("/{param}")
	public ResponseEntity<String> doesThisWork(@RequestParam String param) {
		return new ResponseEntity<String>("Param was '" + param + "'", HttpStatus.OK);
	}

}
