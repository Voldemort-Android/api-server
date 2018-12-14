package voldemort.writter.server.service.rating;

import org.hibernate.validator.constraints.SafeHtml.Tag;

import voldemort.writter.server.persistence.entity.Rating;

public interface RatingService {
	Rating addRating(Rating rating);
	
	Rating editRating (Rating rating);
	
	Rating attachedRating ( Rating rating);

}
