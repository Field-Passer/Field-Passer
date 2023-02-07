package com.example.fieldpasserbe.chat.dto;

import com.example.fieldpasserbe.chat.entity.ChatMessage;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessageResponseDto {
    private int memberId;
    private String content;
    private int status;

    public ChatMessageResponseDto(ChatMessage chatMessage) {
        this.memberId = chatMessage.getMember().getMemberId();
        this.content = chatMessage.getContent();
        this.status = chatMessage.getStatus();
    }
}
