package voldemort.writter.server.service.rating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import voldemort.writter.server.persistence.dao.RatingDao;
import voldemort.writter.server.persistence.dao.StoryDao;
import voldemort.writter.server.persistence.entity.Comment;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.rest.Rating;

@Service
public class RatingServiceImpl {
	
	@Autowired
	RatingDao ratingDao;
	
	@Autowired
	StoryDao storyDao;
	
	
	@PutMapping()
	public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
		comment = ratingService.addComment(rating);
		return new ResponseEntity<>(rating, HttpStatus.OK);
	}
	
	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> getRating(@PathVariable() Long ratingId() {
		Comment comment = ratingDao.findOne(commentId);
		if (comment != null) {
			return new ResponseEntity<>(rating, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/story/{storyId}")
	public ResponseEntity<List<Comment>> getRatingByStory(@PathVariable() Long storyId) {
		return new ResponseEntity<>(RatingDao.findByStory(new Story(storyId)), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
		comment = commentService.editComment(comment);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}
}
