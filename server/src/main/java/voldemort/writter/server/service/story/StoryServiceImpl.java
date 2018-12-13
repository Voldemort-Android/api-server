package voldemort.writter.server.service.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import voldemort.writter.server.exception.RestException;
import voldemort.writter.server.persistence.dao.StoryDao;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.security.AuthenticationUtils;

@Service
public class StoryServiceImpl implements StoryService {
	
	@Autowired
	StoryDao storyDao;

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
		
		return storyDao.update(existingStory);
	}
	
	@Override
	public Integer incrementViews(Long id) {
		Story story = storyDao.findOne(id);
		if (story == null) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Story ID " + id + " does not exist.");
		}
		story.setViews(story.getViews() + 1);
		storyDao.update(story);
		return story.getViews();
	}
	
}
