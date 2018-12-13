package voldemort.writter.server.persistence.dao;

import java.util.List;

import voldemort.writter.server.persistence.entity.Rating;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;

public interface RatingDao {

	Rating createRating(Rating rating);
	
	List<Rating> findByUser(User user);

	Rating findByUserAndStory(User user, Story story);

	Rating updateRating(Rating rating);

}
