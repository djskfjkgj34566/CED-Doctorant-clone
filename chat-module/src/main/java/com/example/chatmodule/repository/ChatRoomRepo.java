package com.example.chatmodule.repository;

import com.example.chatmodule.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepo extends JpaRepository<ChatRoom, Long> {
}
