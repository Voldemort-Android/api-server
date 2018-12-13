package voldemort.writter.server.persistence.dao;

import voldemort.writter.server.persistence.entity.Rating;

public interface RatingDao {

	Rating addRating(Rating rating);

}
