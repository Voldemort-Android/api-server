package voldemort.writter.server.persistence.dao;

import java.util.List;

import voldemort.writter.server.persistence.entity.Rating;
import voldemort.writter.server.persistence.entity.User;

public interface RatingDao {

	Rating addRating(Rating rating);
	
	List<Rating> findByUser(User user);

}
