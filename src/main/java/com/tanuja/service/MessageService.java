package com.tanuja.service;

import com.tanuja.model.Message;

import java.util.List;

public interface MessageService {

	Message sendMessage(Long senderId,Long ChatId,String content)throws Exception;
	List<Message> getMessagesByProjectId(Long projectId) throws Exception;

}
