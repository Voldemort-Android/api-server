package voldemort.writter.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voldemort.writter.server.persistence.dao.CommentDao;
import voldemort.writter.server.persistence.dao.StoryDao;
import voldemort.writter.server.persistence.entity.Comment;
import voldemort.writter.server.persistence.entity.Story;
import voldemort.writter.server.service.comment.CommentService;

@RestController()
@RequestMapping("/rest/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	StoryDao storyDao;
	
	@PostMapping("/{storyId}")
	public ResponseEntity<Comment> addComment(@RequestBody Comment comment, @PathVariable() Long storyId){
		
		Story story = storyDao.findOne(storyId); 
		if (story ==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		comment.setStory(story);
		
		commentService.addComment(comment);

		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("/{storyId}")
	public ResponseEntity<List<Comment>>getCommentsByStory(@PathVariable() Long storyId){
		Story story = storyDao.findOne(storyId);
		if (story ==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Comment> comments = commentDao.findByStory(story);
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
