package voldemort.writter.server.rest;

import java.util.List;

import org.hibernate.validator.constraints.SafeHtml.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voldemort.writter.server.persistence.dao.RecommendationDao;
import voldemort.writter.server.persistence.dao.StoryDao;
import voldemort.writter.server.persistence.dao.TagDao;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;
import voldemort.writter.server.security.AuthenticationUtils;
import voldemort.writter.server.service.story.StoryService;

@RestController()
@RequestMapping("/rest/tag")
public class TagController {

	@Autowired
	StoryDao storyDao;
	
	@Autowired
	StoryService storyService;
	
	@Autowired
	RecommendationDao recommendationDao;
	
	@Autowired
	TagDao tagDao;
	
	@PutMapping()
	public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
		story = storyService.createTag(tag);
		return new ResponseEntity<>(tag, HttpStatus.OK);
	}
	
	@GetMapping("/{storyId}")
	public ResponseEntity<Story> getStory(@PathVariable() Long storyId) {
		Story story = storyDao.findOne(storyId);
		if (story != null) {
			return new ResponseEntity<>(story, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping()
	public ResponseEntity<List<Tag>> getTagsr() {
		User user = AuthenticationUtils.getCurrentUser();
		return new ResponseEntity<List<Story>>(tagDao.findByUser(user), HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Story>> getStoriesByUser(@PathVariable() Long userId) {
		return new ResponseEntity<List<Story>>(storyDao.findByUser(new User(userId)), HttpStatus.OK);
	}
	
	@GetMapping("/recommended")
	public ResponseEntity<List<Tag>> getRecommendedTags() {
		User user = AuthenticationUtils.getCurrentUser();
		return new ResponseEntity<>(recommendationDao.findAllRecommended(new Tag(tag.getId())), HttpStatus.OK);
	}
	

	
	@GetMapping()
	public ResponseEntity<List<Story>> getAllStories() {
		return new ResponseEntity<>(storyDao.findAll(), HttpStatus.OK);
	}
	
	
	

}
