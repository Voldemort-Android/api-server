package voldemort.writter.server.persistence.dao;

import java.util.List;

import voldemort.writter.server.persistence.entity.Comment;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;

public interface CommentDao {

	Comment addComment(Comment comment);
	
	List<Comment> findByStory(Story story);
	
	List<Comment> findByUser(User user);
	
}
