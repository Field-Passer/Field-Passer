package com.example.fieldpasserbe.chat.controller;

import com.example.fieldpasserbe.chat.dto.ChatMessageRequestDto;
import com.example.fieldpasserbe.chat.dto.ChatRoomResponseDto;
import com.example.fieldpasserbe.chat.service.ChatMessageService;
import com.example.fieldpasserbe.chat.service.ChatRoomService;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class StompChatController {

//    private final SimpMessagingTemplate simpMessagingTemplate;
    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;

    @MessageMapping(value = "/chat/enter") //채팅 처음 입장할 때
    public void enter(ChatMessageRequestDto chatMessageRequestDto){
        Member findMember = memberService.findMemberById(chatMessageRequestDto.getMemberId()).get();
        chatMessageRequestDto.setContent(findMember.getMemberName() + "님이 채팅방에 참여하였습니다.");

        // 포스트 아이디와 멤버 아이디를 통해 채팅룸을 찾아오는 로직 (아직 미구현)
        ChatRoomResponseDto findChatRoom = chatRoomService.findByPostIdAndMemberId(chatMessageRequestDto.getPostId(), chatMessageRequestDto.getMemberId()).get();
        chatMessageRequestDto.setChatId(findChatRoom.getChatId());

        chatMessageService.sendMessage(chatMessageRequestDto);

        messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessageRequestDto.getChatId(), chatMessageRequestDto);
    }

    @MessageMapping(value = "/chat/send") //채팅 메시지 보내기
    public void message(ChatMessageRequestDto chatMessageRequestDto) {
        chatMessageService.sendMessage(chatMessageRequestDto);
        messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessageRequestDto.getChatId(), chatMessageRequestDto);
    }
}
