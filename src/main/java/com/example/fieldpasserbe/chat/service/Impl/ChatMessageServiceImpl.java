package com.example.fieldpasserbe.chat.service.Impl;

import com.example.fieldpasserbe.chat.dto.ChatMessageRequestDto;
import com.example.fieldpasserbe.chat.dto.ChatMessageResponseDto;
import com.example.fieldpasserbe.chat.dto.ChatRoomResponseDto;
import com.example.fieldpasserbe.chat.repository.ChatMessageRepositoryJPA;
import com.example.fieldpasserbe.chat.service.ChatMessageService;
import com.example.fieldpasserbe.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepositoryJPA chatMessageRepositoryJPA;
    @Override
    public List<ChatMessageResponseDto> findByChatId(int chatId) {
        return chatMessageRepositoryJPA.findByChatRoom_ChatId(chatId)
                .stream()
                .map(chatMessage -> new ChatMessageResponseDto(chatMessage))
                .collect(Collectors.toList());
    }

    @Override
    public String sendMessage(ChatMessageRequestDto chatMessageRequestDto) {
        try {
            chatMessageRepositoryJPA.save(chatMessageRequestDto.toEntity());
        } catch (Exception e) {
            return "failed";
        }

        return "success";
    }
}
