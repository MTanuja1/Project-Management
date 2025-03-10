package com.tanuja.controller;

import com.tanuja.DTO.IssueDTO;
import com.tanuja.model.Issue;
import com.tanuja.model.User;
import com.tanuja.request.IssueRequest;
import com.tanuja.response.AuthResponse;
import com.tanuja.response.MessageResponse;
import com.tanuja.service.IssueService;
import com.tanuja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

	@Autowired
	private IssueService issueService;

	@Autowired
	private UserService userService;

	@GetMapping("/{issueId}")
	public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
		Issue issue = issueService.getIssueById(issueId);
		return ResponseEntity.ok(issue);

	}

	@GetMapping("/project/{projectId}")
	public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
		return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
	}

	@PostMapping()
	public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue,
												@RequestHeader("Authorization") String token) throws Exception {
		User tokenUser=userService.findUserProfileByJwt(token);
		User user=userService.findUserById(tokenUser.getId());
		Issue newIssue=issueService.createIssue(issue, tokenUser);
		IssueDTO issueDTO=new IssueDTO();
		issueDTO.setId(newIssue.getId());
		issueDTO.setTitle(newIssue.getTitle());
		issueDTO.setDescription(newIssue.getDescription());
		issueDTO.setDueDate(newIssue.getDueDate());
		issueDTO.setPriority(newIssue.getPriority());
		issueDTO.setProject(newIssue.getProject());
		issueDTO.setStatus(newIssue.getStatus());
		issueDTO.setTags(newIssue.getTags());
		issueDTO.setAssignee(newIssue.getAssignee());
		return ResponseEntity.ok(issueDTO);

	}

	@DeleteMapping("/{issueId}")
	public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
													@RequestHeader("Authorization")String token) throws Exception {
		User user=userService.findUserProfileByJwt(token);
		issueService.deleteIssue(issueId,user.getId());
		MessageResponse res=new MessageResponse();
		res.setMessage("Issue deleted successfully");
		return ResponseEntity.ok(res);
	}

	@PutMapping("/{issueId}/assignee/{userId}")
	public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,
										   @PathVariable Long userId) throws Exception{
		Issue issue=issueService.addUserToIssue(issueId,userId);
		return ResponseEntity.ok(issue);
	}

	@PutMapping("/{issueId}/status/{status}")
	public ResponseEntity<Issue> updateIssueStatus(@PathVariable String status,
												   @PathVariable Long issueId) throws  Exception{
		Issue issue=issueService.updateStatus(issueId,status);
		return ResponseEntity.ok(issue);
	}
}
