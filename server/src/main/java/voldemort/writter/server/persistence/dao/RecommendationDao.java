package voldemort.writter.server.persistence.dao;

import java.util.List;

import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;

public interface RecommendationDao {
	
	List<Story> findAllRecommended(User user);

}
