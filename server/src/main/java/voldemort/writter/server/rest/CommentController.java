package voldemort.writter.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voldemort.writter.server.persistence.dao.CommentDao;
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
	
	@PutMapping()
	public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
		comment = commentService.addComment(comment);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}
	
	@GetMapping("/{storyId}")
	public ResponseEntity<List<Comment>> getCommentsByStory(@PathVariable() Long storyId) {
		return new ResponseEntity<>(commentDao.findByStory(new Story(storyId)), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
		// TODO Implement this
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
