package com.ll.exam.chat;

import java.util.List;

import com.ll.exam.chat.dto.ChatRoomDto;

public class ChatService {
	private ChatRoomRepository chatRoomRepository;

	public ChatService() {
		chatRoomRepository = new ChatRoomRepository();
	}
	public long createRoom(String title, String body) {
		return chatRoomRepository.create(title, body);
	}

	public List<ChatRoomDto> findAllRooms() {
		return chatRoomRepository.findAll();
	}
}

