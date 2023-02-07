package com.example.fieldpasserbe.chat.dto;

import com.example.fieldpasserbe.chat.entity.ChatMessage;
import com.example.fieldpasserbe.chat.entity.ChatRoom;
import com.example.fieldpasserbe.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessageRequestDto {

    private int chatId;
    private int postId; //chatId 찾기용
    private int memberId;
    private String content;

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .chatRoom(ChatRoom.builder().chatId(chatId).build())
                .member(Member.builder().memberId(memberId).build())
                .content(content)
                .build();
    }
}
