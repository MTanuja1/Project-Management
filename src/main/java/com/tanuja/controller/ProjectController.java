package com.tanuja.controller;

import com.tanuja.model.Chat;
import com.tanuja.model.Invitation;
import com.tanuja.model.Project;
import com.tanuja.model.User;
import com.tanuja.repository.InviteRequest;
import com.tanuja.response.MessageResponse;
import com.tanuja.service.InvitationService;
import com.tanuja.service.ProjectService;
import com.tanuja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	@Autowired
	private InvitationService invitationService;

	@GetMapping()
	public ResponseEntity<List<Project>> getProjects(@RequestParam(required = false) String category,
													 @RequestParam(required = false) String tag,
													 @RequestHeader("Authorization")String jwt)throws Exception {
		User user=userService.findUserProfileByJwt(jwt);
		List<Project> projects=projectService.getAllProjectByTeam(user,category,tag);
		return new ResponseEntity<>(projects, HttpStatus.OK);


	}
	@GetMapping("/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable Long projectId,
													 @RequestHeader("Authorization")String jwt)throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		Project projects = projectService.getProjectById(projectId);
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Project> createProject(@RequestBody Project project,
													 @RequestHeader("Authorization")String jwt)throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		Project projects = projectService.createProject(project, user);
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}

	@PatchMapping("/{projectId}")
	public ResponseEntity<Project> updateProject(@PathVariable Long projectId,
												 @RequestBody Project project,
												 @RequestHeader("Authorization")String jwt)throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		Project projects = projectService.updateProject(project,projectId);
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}

	@DeleteMapping("/{projectId}")
	public ResponseEntity<MessageResponse> deleteProject(@PathVariable Long projectId,
										  @RequestHeader("Authorization") String jwt)throws  Exception{
		User user = userService.findUserProfileByJwt(jwt);
		projectService.deleteProject(projectId,user.getId());
		MessageResponse res =new MessageResponse("Project deleted Successfully");
		return new ResponseEntity<>(res,HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Project>> searchProjects(@RequestParam(required = false) String keyword,
													 @RequestHeader("Authorization")String jwt)throws Exception {
		User user=userService.findUserProfileByJwt(jwt);
		List<Project> projects=projectService.searchProjects(keyword,user);
		return new ResponseEntity<>(projects, HttpStatus.OK);


	}


	@GetMapping("/{projectId}/chat")
	public ResponseEntity<Chat> getChatByProjectById(@PathVariable Long projectId,
												  @RequestHeader("Authorization")String jwt)throws Exception {
		User user = userService.findUserProfileByJwt(jwt);
		Chat chat = projectService.getChatByProjectId(projectId);
		return new ResponseEntity<>(chat, HttpStatus.OK);
	}

	@PostMapping("/invite")
	public ResponseEntity<MessageResponse> inviteProject(@RequestBody InviteRequest req,
														 @RequestHeader("Authorization")String jwt,
														 @RequestBody Project project)throws Exception {
		User user=userService.findUserProfileByJwt(jwt);
		invitationService.sendInvitation(req.getEmail(),req.getProjectId());
		MessageResponse response=new MessageResponse("User invitation sent");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@GetMapping("/accept_invitation")
	public ResponseEntity<Invitation> acceptInvitationProject(@RequestParam String token,
														 @RequestHeader("Authorization")String jwt,
														 @RequestBody Project project)throws Exception {
		User user=userService.findUserProfileByJwt(jwt);
		Invitation invitation=invitationService.acceptInvitation(token,user.getId());
		projectService.addUserToProject(invitation.getProjectId(),project.getId());
		return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
	}
	}
