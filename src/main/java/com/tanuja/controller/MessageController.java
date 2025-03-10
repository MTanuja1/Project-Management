package com.tanuja.controller;


import com.tanuja.model.Chat;
import com.tanuja.model.Message;
import com.tanuja.model.User;
import com.tanuja.request.CreateMessageRequest;
import com.tanuja.service.MessageService;
import com.tanuja.service.ProjectService;
import com.tanuja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;

	@Autowired
	private MessageService messageService;


	@PostMapping("/send")
	public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request)throws Exception {
		User user =userService.findUserById(request.getSenderId());
		Chat chats=projectService.getProjectById(request.getProjectId()).getChat();
		if (chats==null){
			throw new Exception("chat not found");
		}
		Message message=messageService.sendMessage(request.getSenderId(),
				request.getProjectId(), request.getContent());
		return ResponseEntity.ok(message);
	}


	@GetMapping("/chat/{projectId}")
	public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception {
		List<Message> messageList=messageService.getMessagesByProjectId(projectId);
		return ResponseEntity.ok(messageList);
	}
}
