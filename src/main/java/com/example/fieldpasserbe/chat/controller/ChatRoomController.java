package com.example.fieldpasserbe.chat.controller;

import com.example.fieldpasserbe.chat.dto.ChatMessageRequestDto;
import com.example.fieldpasserbe.chat.dto.ChatMessageResponseDto;
import com.example.fieldpasserbe.chat.dto.ChatRoomRequestDto;
import com.example.fieldpasserbe.chat.dto.ChatRoomResponseDto;
import com.example.fieldpasserbe.chat.service.ChatMessageService;
import com.example.fieldpasserbe.chat.service.ChatRoomService;
import com.example.fieldpasserbe.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    @GetMapping("/chat/rooms") // 내 채팅 목록 조회
    public List<ChatRoomResponseDto> findMyChatRooms(@RequestParam int memberId) {
        return chatRoomService.findMyChatRooms(memberId);
    }

    @PostMapping("/chat/room") //채팅방 생성 (dto에 처음에는 postId, buyerId만 들어감 buyerId는 세션을 통해 가져옴)
    public String createChatRoom(ChatRoomRequestDto chatRoomRequestDto, HttpSession session) {

//        int memberId = Integer.parseInt(session.getAttribute("memberId").toString());
//        chatRoomRequestDto.setBuyerId(memberId);

        return chatRoomService.createChatRoom(chatRoomRequestDto);
    }

    @GetMapping("/chat/room/{chatId}") //채팅방 입장(해당 채팅방 메시지 목록 가져옴)
    public List<ChatMessageResponseDto> enterChatRoom(@PathVariable int chatId) {
        return chatMessageService.findByChatId(chatId);
    }
}
