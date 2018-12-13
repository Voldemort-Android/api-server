package voldemort.writter.server.persistence.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import voldemort.writter.server.persistence.dao.StoryDao;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.Tag;
import voldemort.writter.server.persistence.entity.User;

@Repository
public class StoryDaoImpl implements StoryDao {
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Story> findByTag(Tag tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> findAll() {
		// FIXME Fix this
		return null;
	}
	
	@Override
	public List<Story> findByPage(int page, int limit) {
		return entityManager.createQuery("from Story order by id", Story.class)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
	}

	@Override
	@Transactional
	public Story update(Story story) {
		story.setModified(new Date());
		return entityManager.merge(story);
	}

}
