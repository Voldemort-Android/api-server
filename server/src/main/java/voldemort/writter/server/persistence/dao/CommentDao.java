package voldemort.writter.server.persistence.dao;

import java.util.List;

import voldemort.writter.server.persistence.entity.Comment;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;

public interface CommentDao {

	Comment createComment(Comment comment);
	
	Comment findOne(Long id);
	
	List<Comment> findByStory(Story story);
	
	List<Comment> findByUser(User user);

	Comment updateComment(Comment comment);
	
}
