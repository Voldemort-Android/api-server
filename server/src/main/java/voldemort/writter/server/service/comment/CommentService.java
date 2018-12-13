package voldemort.writter.server.service.comment;

import voldemort.writter.server.persistence.entity.Comment;

public interface CommentService {
	
	Comment addComment(Comment comment);
	
}
