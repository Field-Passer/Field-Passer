package com.example.fieldpasserbe.chat.service;

import com.example.fieldpasserbe.chat.dto.ChatRoomRequestDto;
import com.example.fieldpasserbe.chat.dto.ChatRoomResponseDto;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {

    String createChatRoom(ChatRoomRequestDto chatRoomRequestDto);
    List<ChatRoomResponseDto> findMyChatRooms(int memberId);
    Optional<ChatRoomResponseDto> findByPostIdAndMemberId(int postId, int memberId);
}
