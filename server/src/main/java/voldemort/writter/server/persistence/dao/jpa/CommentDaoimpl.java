package voldemort.writter.server.persistence.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import voldemort.writter.server.persistence.dao.CommentDao;
import voldemort.writter.server.persistence.entity.Comment;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;

@Repository
public class CommentDaoimpl implements CommentDao{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@Override
	@Transactional
	public Comment addComment(Comment comment) {
		
		Date now = new Date();
		comment.setCreated(now);
		comment.setModified(now);
		
		entityManager.merge(comment);
		
		return comment;
	}

	@Override
	public List<Comment> findByStory(Story story) {
		
		try {
			return entityManager.createQuery("from Comment where story = :story",Comment.class)
					.setParameter("story", story)
					.getResultList();
		}catch(NoResultException e) {
			return null;
		}
		
	
	}

	@Override
	public List<Comment> findByUser(User user) {
	
		try {
			return entityManager.createQuery("from Comment where user = :user",Comment.class)
					.setParameter("user", user)
					.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}

}
