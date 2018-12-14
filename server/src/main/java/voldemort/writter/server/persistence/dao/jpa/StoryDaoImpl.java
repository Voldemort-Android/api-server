package voldemort.writter.server.persistence.dao.jpa;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import voldemort.writter.server.persistence.dao.StoryDao;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;
import voldemort.writter.server.persistence.entity.Recommendation;

@Repository
public class StoryDaoImpl implements StoryDao {
	
	private final String liteQuery = 
			"Select new Story(id, title, views, created, modified, author) from Story s";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public Story createStory(Story story) {

		// Set the created and modified dates
		Date now = new Date();
		story.setCreated(now);
		story.setModified(now);
		
		return entityManager.merge(story);
	}

	@Override
	public Story findOne(Long id) {
		return entityManager.find(Story.class, id);
	}

	@Override
	public List<Story> findByUser(User user) {
		return entityManager.createQuery(liteQuery + " where s.author=:user order by s.created desc", Story.class)
				.setParameter("user", user)
				.getResultList();
	}

	@Override
	public List<Story> findAll() {
		return entityManager.createQuery(liteQuery, Story.class)
				.getResultList();
	}
	
	@Override
	public List<Story> findAllRecommended(User user){
		String sqlString = "Select s.id from stories s"; 
		sqlString += " LEFT JOIN recommendation r on s.id = r.story_id WHERE r.user_id=:user";
		return entityManager.createQuery("from Recommendation where user=:user", Recommendation.class)
                .setParameter("user", user)
                .getResultList()
                .stream()
                .map(r -> r.getStory())
                .collect(Collectors.toList());
	}
	
	@Override
	public List<Story> findByPage(int page, int limit) {
		return entityManager.createQuery(liteQuery + " order by s.created desc", Story.class)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
	}

	@Override
	@Transactional
	public Story updateStory(Story story) {
		// TODO Check if story ID actually already exists.
		story.setModified(new Date());
		return entityManager.merge(story);
	}

}
