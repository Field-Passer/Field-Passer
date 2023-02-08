package com.example.fieldpasserbe.chat.service;

import com.example.fieldpasserbe.chat.dto.ChatMessageRequestDto;
import com.example.fieldpasserbe.chat.dto.ChatMessageResponseDto;
import com.example.fieldpasserbe.chat.dto.ChatRoomRequestDto;

import java.util.List;

public interface ChatMessageService {
    List<ChatMessageResponseDto> findByChatId(int chatId);
    String sendMessage(ChatMessageRequestDto chatMessageRequestDto);
}
