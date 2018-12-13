package voldemort.writter.server.persistence.dao;

import voldemort.writter.server.persistence.entity.Story;

public interface StoryDao {
	
	Story createStory(Story story);

}
