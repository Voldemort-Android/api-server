package voldemort.writter.server.persistence.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import voldemort.writter.server.persistence.dao.RatingDao;
import voldemort.writter.server.persistence.entity.Rating;
import voldemort.writter.server.persistence.entity.User;
import voldemort.writter.server.security.AuthenticationUtils;

@Repository
public class RatingDaoImpl implements RatingDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public Rating addRating(Rating rating) {
		
		// Set this to null...ID will be automatically assigned.
		rating.setId(null);
		
		// Set created and modified dates.
		Date now = new Date();
		rating.setCreated(now);
		rating.setModified(now);
		
		// Set the user
		rating.setUser(AuthenticationUtils.getCurrentUser());
		
		entityManager.merge(rating);
		return rating;
		
	}

	@Override
	public List<Rating> findByUser(User user) {
		return entityManager.createQuery("from Rating where user = :user", Rating.class)
				.setParameter("unit", user)
				.getResultList();
	}

}
