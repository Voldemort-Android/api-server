package voldemort.writter.server.persistence.dao;

import java.util.List;

import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;

public interface StoryDao {
	
	Story createStory(Story story);
	
	Story findOne(Long id);
	
	List<Story> findByUser(User user);
	
	List<Story> findAll();
	
	List<Story> findAllRecommended(User user);
	
	List<Story> findByPage(int page, int limit);
	
	Story updateStory(Story story);

}
