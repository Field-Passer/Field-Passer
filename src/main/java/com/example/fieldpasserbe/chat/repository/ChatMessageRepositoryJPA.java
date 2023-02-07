package com.example.fieldpasserbe.chat.repository;

import com.example.fieldpasserbe.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepositoryJPA extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoom_ChatId(int chatId);
}
