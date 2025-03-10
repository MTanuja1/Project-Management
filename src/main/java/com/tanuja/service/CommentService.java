package com.tanuja.service;

import com.tanuja.model.Comment;

import java.util.List;

public interface CommentService {

	Comment creteComment(Long issueId,Long userId,String comment) throws Exception;
	void deleteComment(Long commentId,Long userId) throws Exception;
	List<Comment> findCommentByIssueId(Long issueId);
}
