package com.tanuja.service;

import com.tanuja.model.Issue;
import com.tanuja.model.User;
import com.tanuja.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {

	Issue getIssueById(Long id)throws Exception;
	List<Issue> getIssueByProjectId(Long projectId)throws Exception;
	Issue createIssue(IssueRequest issue, User user)throws Exception;
	void deleteIssue(Long issueId,Long userId) throws Exception;
	Issue addUserToIssue(Long issueId,Long userId)throws Exception;
	Issue updateStatus(Long issueId,String status) throws Exception;

}
