package voldemort.writter.server.persistence.dao.jpa;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import voldemort.writter.server.persistence.dao.RecommendationDao;
import voldemort.writter.server.persistence.entity.Recommendation;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;

@Repository
public class RecommendationDaoImpl implements RecommendationDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Story> findAllRecommended(User user) {
		
		return entityManager.createQuery("from Recommendation where user=:user", Recommendation.class)
				.setParameter("user", user)
				.getResultList()
				.stream()
				.map(r -> r.getStory())
				.collect(Collectors.toList());

	}

}
