package voldemort.writter.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voldemort.writter.server.persistence.dao.StoryDao;
import voldemort.writter.server.persistence.entity.Rating;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;
import voldemort.writter.server.security.AuthenticationUtils;
import voldemort.writter.server.service.story.StoryService;

@RestController()
@RequestMapping("/rest/story")
public class StoryController {
	
	@Autowired
	StoryDao storyDao;
	
	@Autowired
	StoryService storyService;
	
	@PostMapping()
	public ResponseEntity<Story> createStory(@RequestBody Story story) {
		// TODO Implement this
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/{storyId}")
	public ResponseEntity<Story> getStory(@PathVariable() Long storyId) {
		Story story = storyDao.findOne(storyId);
		if (story != null) {
			return new ResponseEntity<>(story, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<Story>> getStoryByUser() {
		User user = AuthenticationUtils.getCurrentUser();
		return new ResponseEntity<List<Story>>(storyDao.findByUser(user), HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Story>> getStoryByUser(@PathVariable() Long userId) {
		return new ResponseEntity<List<Story>>(storyDao.findByUser(new User(userId)), HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<Story>> getAllStories() {
		return new ResponseEntity<>(storyDao.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/page/{page}/limit/{limit}")
	public ResponseEntity<List<Story>> getPaginatedStories(@PathVariable() int page, @PathVariable() int limit) {
		return new ResponseEntity<>(storyDao.findByPage(page, limit), HttpStatus.OK);
	}
	
	// Very bad way to do this, but oh wells.
	@PostMapping("/update-views/{storyId}")
	public ResponseEntity<Object> incrementViews(@PathVariable() Long storyId) {
		Integer views = storyService.incrementViews(storyId);
		if (views != null) {
			return new ResponseEntity<>(views, HttpStatus.OK);
		}
		return new ResponseEntity<>("Unknow error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/{storyId}/rate/{score}")
	public ResponseEntity<Object> rateStory(@PathVariable() Long storyId, @PathVariable() Double score) {
		Rating rating = storyService.rateStory(storyId, score);
		return new ResponseEntity<>(rating, HttpStatus.OK);
	}

}
