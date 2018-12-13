package voldemort.writter.server.service.comment;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import voldemort.writter.server.exception.RestException;
import voldemort.writter.server.persistence.dao.CommentDao;
import voldemort.writter.server.persistence.dao.StoryDao;
import voldemort.writter.server.persistence.entity.Comment;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.persistence.entity.User;
import voldemort.writter.server.security.AuthenticationUtils;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	StoryDao storyDao;

	@Override
	public Comment addComment(Comment comment) {
		
		Story story = comment.getStory();
		String text = comment.getText();
		
		// Data validation
		if (StringUtils.isEmpty(text)) {
			throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Comment text cannot be blank");
		}
		if (story == null || storyDao.findOne(story.getId()) == null) {
			throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Story is missing or does not exist");
		}

		Comment newComment = new Comment();
		
		// Set the author
		comment.setUser(AuthenticationUtils.getCurrentUser());
		
		// Copy info over from request object
		newComment.setText(comment.getText());
		newComment.setStory(comment.getStory());
		
		return commentDao.createComment(comment);
	}

	@Override
	public Comment editComment(Comment comment) {
		
		Long id = comment.getId();
		Comment existingComment = commentDao.findOne(id);
		
		if (existingComment == null) {
			throw new RestException(HttpStatus.BAD_REQUEST, "Comment ID " + id + " does not exist");
		}
		
		// Only the original author of the story can update it
		User user = AuthenticationUtils.getCurrentUser();
		if (!Objects.equals(user.getId(), existingComment.getUser().getId())) {
			throw new RestException(HttpStatus.UNAUTHORIZED, "You do not have permission to edit this comment");
		}
		
		Story story = comment.getStory();
		String text = comment.getText();
		
		// Data validation
		if (StringUtils.isEmpty(text)) {
			throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Comment text cannot be blank");
		}
		if (story == null || storyDao.findOne(story.getId()) == null) {
			throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Story is missing or does not exist");
		}
		
		// Copy info over from request object
		existingComment.setText(comment.getText());
		existingComment.setStory(comment.getStory());
		
		return commentDao.updateComment(existingComment);
		
	}

}
