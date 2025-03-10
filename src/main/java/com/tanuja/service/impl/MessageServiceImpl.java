package com.tanuja.service.impl;

import com.tanuja.model.Chat;
import com.tanuja.model.Message;
import com.tanuja.model.User;
import com.tanuja.repository.MessageRepository;
import com.tanuja.repository.UserRepository;
import com.tanuja.service.MessageService;
import com.tanuja.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProjectService projectService;


	@Override
	public Message sendMessage(Long senderId, Long ChatId, String content) throws Exception{
		User user = userRepository.findById(senderId).orElseThrow(()->new Exception("user not found"));
		Chat chat=projectService.getProjectById(ChatId).getChat();
		Message message=new Message();
		message.setSender(user);
		message.setChat(chat);
		message.setContent(content);
		message.setCreatedAt(LocalDateTime.now());
		Message savedMessage=messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		return savedMessage;
	}

	@Override
	public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
		Chat chat=projectService.getChatByProjectId(projectId);
		List<Message> messageList=messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
		return messageList;
	}
}
