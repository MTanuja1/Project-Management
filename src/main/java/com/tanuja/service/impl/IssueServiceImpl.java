package com.tanuja.service.impl;

import com.tanuja.model.Issue;
import com.tanuja.model.Project;
import com.tanuja.model.User;
import com.tanuja.repository.IssueRepository;
import com.tanuja.request.IssueRequest;
import com.tanuja.service.IssueService;
import com.tanuja.service.ProjectService;
import com.tanuja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

@Autowired
	private IssueRepository issueRepository;

@Autowired
private ProjectService projectService;

@Autowired
private UserService	userService;

	@Override
	public Issue getIssueById(Long id) throws Exception {
		Optional<Issue> issue = issueRepository.findById(id);
		if (issue.isPresent()) {
			return issue.get();
		}
		throw new Exception("No issue found with issueId");
	}

	@Override
	public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
		return  issueRepository.findByProjectId(projectId);
	}

	@Override
	public Issue createIssue(IssueRequest issue, User user) throws Exception {
		Project project = projectService.getProjectById(issue.getProjectId());
		Issue newIssue = new Issue();
		newIssue.setTitle(issue.getTitle());
		newIssue.setDescription(issue.getDescription());
		newIssue.setStatus(issue.getStatus());
		newIssue.setProjectID(newIssue.getProjectID());
		newIssue.setPriority(issue.getPriority());
		newIssue.setDueDate(issue.getDueDate());
		newIssue.setProject(project);
		return issueRepository.save(newIssue);
	}

	@Override
	public void deleteIssue(Long issueId, Long userId) throws Exception {
		getIssueById(issueId);
		issueRepository.deleteById(issueId);
	}

	@Override
	public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
		User user =userService.findUserById(userId);
		Issue issue=getIssueById(issueId);
		issue.setAssignee(user);
		return issueRepository.save(issue);
	}

	@Override
	public Issue updateStatus(Long issueId, String status) throws Exception {
		Issue issue=getIssueById(issueId);
		issue.setStatus(status);
		return issueRepository.save(issue);
	}
}
