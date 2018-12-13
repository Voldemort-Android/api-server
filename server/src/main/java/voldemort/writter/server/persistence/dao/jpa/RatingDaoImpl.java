package voldemort.writter.server.persistence.dao.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import voldemort.writter.server.dto.security.AuthenticationDetails;
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
		AuthenticationDetails authenticationDetails = AuthenticationUtils.getDetails();
		rating.setUser(new User(authenticationDetails.getId())); // We only need the ID
		
		entityManager.merge(rating);
		return rating;
	}

}
