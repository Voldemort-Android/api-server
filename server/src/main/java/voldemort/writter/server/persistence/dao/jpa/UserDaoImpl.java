package voldemort.writter.server.persistence.dao.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import voldemort.writter.server.persistence.dao.UserDao;
import voldemort.writter.server.persistence.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public User createUser(User user) {
		
		// Set this to null...ID will be automatically assigned.
		user.setId(null);
		
		// Set created and modified dates.
		Date now = new Date();
		user.setCreated(now);
		user.setModified(now);
		
		entityManager.merge(user);
		return user;
	}
	
	@Override
	public User findByUsername(String username) {
		try {
			return entityManager.createQuery("from User where username=:username", User.class)
					.setParameter("username", username)
					.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public User findByEmail(String email) {
		try {
			return entityManager.createQuery("from User where email=:email", User.class)
					.setParameter("email", email)
					.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

}
