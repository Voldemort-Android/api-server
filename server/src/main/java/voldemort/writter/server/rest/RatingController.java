package voldemort.writter.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voldemort.writter.server.persistence.entity.Rating;
import voldemort.writter.server.service.rating.RatingService;

@RestController()
@RequestMapping("/rest/rating")
public class RatingController {

	@Autowired
	RatingService ratingService;
	
	@PostMapping()
	public ResponseEntity<Long> addRating(@RequestBody Rating rating) {
		ratingService.addRating(rating);
		return new ResponseEntity<Long>(rating.getId(), HttpStatus.OK);
	}
	
}
