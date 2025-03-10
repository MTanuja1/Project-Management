package com.tanuja.controller;

import com.tanuja.model.Comment;
import com.tanuja.model.User;
import com.tanuja.request.CreateCommentRequest;
import com.tanuja.response.MessageResponse;
import com.tanuja.service.CommentService;
import com.tanuja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/comments")
@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;

	@PostMapping()
	public ResponseEntity<Comment> createComment(@RequestBody CreateCommentRequest req,
												 @RequestHeader("Authorization") String jwt)throws Exception {
		User user=userService.findUserProfileByJwt(jwt);
		Comment createComment=commentService.creteComment(req.getIssueeId(),
				user.getId(),
				req.getContent());
		return new ResponseEntity<>(createComment, HttpStatus.CREATED);

	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
														 @RequestHeader("Authorization")String jwt)throws Exception {
		User user=userService.findUserProfileByJwt(jwt);
		commentService.deleteComment(commentId,user.getId());
		MessageResponse messageResponse=new MessageResponse();
		messageResponse.setMessage("comment deleted successfully");
		return new ResponseEntity<>(messageResponse, HttpStatus.OK);
	}

	@GetMapping("/{issueId}")
	public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId){
		List<Comment> comments=commentService.findCommentByIssueId(issueId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
}

