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

	public ChatRoomDto findRoomById(long id) {
		return chatRoomRepository.findById(id);
	}

	public void modifyRoom(long id, String title, String body) {
		chatRoomRepository.modify(id, title, body);
	}
}

