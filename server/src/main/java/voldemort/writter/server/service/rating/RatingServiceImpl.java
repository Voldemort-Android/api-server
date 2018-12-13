package voldemort.writter.server.service.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import voldemort.writter.server.persistence.dao.RatingDao;
import voldemort.writter.server.persistence.entity.Rating;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	RatingDao ratingDao;
	
	@Override
	public Rating addRating(Rating rating) {
		// TODO Add data validation.
		
		// User should only have one rating per story.
		
		return ratingDao.addRating(rating);
	}

}
