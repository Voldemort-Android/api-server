package voldemort.writter.server.service.rating;

import voldemort.writter.server.persistence.entity.Rating;

public interface RatingService {

	Rating addRating(Rating rating);
	
}
