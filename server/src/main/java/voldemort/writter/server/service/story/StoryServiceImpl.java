package voldemort.writter.server.service.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import voldemort.writter.server.exception.RestException;
import voldemort.writter.server.persistence.dao.RatingDao;
import voldemort.writter.server.persistence.dao.StoryDao;
import voldemort.writter.server.persistence.entity.Rating;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;
import voldemort.writter.server.security.AuthenticationUtils;

@Service
public class StoryServiceImpl implements StoryService {
	
	@Autowired
	StoryDao storyDao;
	
	@Autowired
	RatingDao ratingDao;

	@Override
	public Story createStory(Story story) {
		
		Story newStory = new Story();
		
		// Set the author
		story.setAuthor(AuthenticationUtils.getCurrentUser());
		
		// Copy info over from request object
		newStory.setTitle(story.getTitle());
		newStory.setText(story.getText());
		
		return storyDao.createStory(story);
	}

	@Override
	public Story updateStory(Story story) {
		
		Long id = story.getId();
		Story existingStory = storyDao.findOne(id);
		
		if (existingStory == null) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Story ID " + id + " does not exist.");
		}
		
		// Copy info over from request object
		existingStory.setTitle(story.getTitle());
		existingStory.setText(story.getText());
		existingStory.setAuthor(story.getAuthor());
		
		return storyDao.updateStory(existingStory);
	}
	
	@Override
	public Integer incrementViews(Long id) {
		Story story = storyDao.findOne(id);
		if (story == null) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Story ID " + id + " does not exist.");
		}
		story.setViews(story.getViews() + 1);
		storyDao.updateStory(story);
		return story.getViews();
	}
	
	@Override
	public Rating rateStory(Long id, Double score) {
		
		User user = AuthenticationUtils.getCurrentUser();
		Story story = new Story(id);
		
		// Check if a rating already exists from the user for the story.
		Rating rating = ratingDao.findByUserAndStory(user, story);
		
		// Update the rating if it already exists.
		if (rating != null) {
			rating.setRating(score);
			ratingDao.updateRating(rating);
		}
		
		// Else create a new rating
		else {
			rating = new Rating();
			rating.setUser(user);
			rating.setStory(story);
			rating.setRating(score);
			ratingDao.createRating(rating);
		}
		
		return rating;
	}
	
}
