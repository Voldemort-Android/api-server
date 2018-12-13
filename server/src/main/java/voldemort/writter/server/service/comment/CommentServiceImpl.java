package voldemort.writter.server.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import voldemort.writter.server.persistence.dao.CommentDao;
import voldemort.writter.server.persistence.entity.Comment;
import voldemort.writter.server.security.AuthenticationUtils;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentDao commentDao;
	
	@Override
	public Comment addComment(Comment comment) {
		
		comment.setUser(AuthenticationUtils.getCurrentUser()); 

		return commentDao.addComment(comment);
		
	}

}
