package com.tanuja.service.impl;

import com.tanuja.model.Comment;
import com.tanuja.model.Issue;
import com.tanuja.model.User;
import com.tanuja.repository.CommentRepository;
import com.tanuja.repository.IssueRepository;
import com.tanuja.repository.UserRepository;
import com.tanuja.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private CommentRepository commentRepository;


	@Override
	public Comment creteComment(Long issueId, Long userId, String comment) throws Exception {
		Optional<Issue> issue = issueRepository.findById(issueId);
		Optional<User> user = userRepository.findById(userId);
		if(issue.isEmpty() || user.isEmpty()){
			throw new Exception("issue not found");
		}
		Issue issueObj = issue.get();
		User userObj = user.get();
		Comment comment1=new Comment();
		comment1.setIssue(issueObj);
		comment1.setUser(userObj);
		comment1.setContent(comment);
		comment1.setCreatedDateTime(LocalDateTime.now());
		commentRepository.save(comment1);
		issueObj.getComments().add(comment1);

		return comment1;
	}

	@Override
	public void deleteComment(Long commentId, Long userId) throws Exception {
		Optional<Comment> commentOptional=commentRepository.findById(commentId);
		Optional<User> userOptional=userRepository.findById(userId);
		if(commentOptional.isEmpty() || userOptional.isEmpty()){
			throw new Exception("comment not foud");
		}
		Comment comment=commentOptional.get();
		User user=userOptional.get();
		if(comment.getUser().equals(user)){
			commentRepository.delete(comment);
		}else{
			throw new Exception("user does not have permission to delete this comment");
		}

	}

	@Override
	public List<Comment> findCommentByIssueId(Long issueId) {
		return commentRepository.findByIssueId(issueId);
	}
}
