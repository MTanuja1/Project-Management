package com.tanuja.service.impl;

import com.tanuja.model.Chat;
import com.tanuja.repository.ChatRepository;
import com.tanuja.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
	@Autowired
	private ChatRepository chatRepository;
	@Override
	public Chat createChat(Chat chat) {
			return chatRepository.save(chat);
	}
}
