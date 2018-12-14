package voldemort.writter.server.rest;

import java.util.List;

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

import voldemort.writter.server.persistence.dao.RatingDao;
import voldemort.writter.server.persistence.dao.RecommendationDao;
import voldemort.writter.server.persistence.dao.StoryDao;
import voldemort.writter.server.persistence.dao.TagDao;
import voldemort.writter.server.persistence.entity.Comment;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.service.story.StoryService;

@RestController()
@RequestMapping("/rest/rating")
public class RatingController {
	
	@Autowired
	StoryDao storyDao;
	
	@Autowired
	StoryService storyService;
	
	@Autowired
	RecommendationDao recommendationDao;
	
	@Autowired
	TagDao tagDao;
	
	@Autowired
	RatingDao ratingDao;
	
	
	
	
	
}
