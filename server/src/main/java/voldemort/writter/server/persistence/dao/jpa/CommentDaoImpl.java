package voldemort.writter.server.persistence.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import voldemort.writter.server.persistence.dao.CommentDao;
import voldemort.writter.server.persistence.entity.Comment;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;
import voldemort.writter.server.security.AuthenticationUtils;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Comment createComment(Comment comment) {
		
		// Set this to null...ID will be automatically assigned.
		comment.setId(null);
		
		// Set created and modified dates.
		Date now = new Date();
		comment.setCreated(now);
		comment.setModified(now);
		
		// Set the user
		comment.setUser(AuthenticationUtils.getCurrentUser());
		
		entityManager.merge(comment);
		return comment;
	}

	@Override
	public Comment findOne(Long id) {
		return entityManager.find(Comment.class, id);
	}
	
	@Override
	public List<Comment> findByStory(Story story) {
		return entityManager.createQuery("from Comment where story=:story", Comment.class)
				.setParameter("story", story)
				.getResultList();
	}

	@Override
	public List<Comment> findByUser(User user) {
		return entityManager.createQuery("from Comment where user=:user", Comment.class)
				.setParameter("user", user)
				.getResultList();
	}
	
	@Override
	@Transactional
	public Comment updateComment(Comment comment) {
		// TODO Check if comment ID actually already exists.
		comment.setModified(new Date());
		return entityManager.merge(comment);
	}

}
