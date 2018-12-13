package voldemort.writter.server.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import voldemort.writter.server.persistence.entity.Story;

public class StoryController {
	
	@PostMapping()
	public ResponseEntity<Long> createStory(@RequestBody Story story) {
		// TODO Implement this
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
