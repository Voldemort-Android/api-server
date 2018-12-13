package voldemort.writter.server.service.story;

import voldemort.writter.server.persistence.entity.Rating;
import voldemort.writter.server.persistence.entity.Story;

public interface StoryService {
	
	Story createStory(Story story);
	
	Story updateStory(Story story);
	
	Integer incrementViews(Long id);

	Rating rateStory(Long id, Double rating);

}
