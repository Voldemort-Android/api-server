package voldemort.writter.server.persistence.dao;

import java.util.List;

import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.Tag;
import voldemort.writter.server.persistence.entity.User;

public interface StoryDao {
	
	Story createStory(Story story);
	
	Story findOne(Long id);
	
	List<Story> findByUser(User user);
	
	List<Story> findByTag(Tag tag);
	
	List<?> findAll();
	
	List<Story> findByPage(int page, int limit);
	
	Story update(Story story);

}
