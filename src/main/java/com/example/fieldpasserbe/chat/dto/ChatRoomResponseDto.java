package com.example.fieldpasserbe.chat.dto;

import com.example.fieldpasserbe.chat.entity.ChatRoom;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomResponseDto {

    private int chatId;
    private int sellerId;
    private String sellerName;
    private int buyerId;
    private String buyerName;
    private int postId;

    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.chatId = chatRoom.getChatId();
        this.sellerId = chatRoom.getSeller().getMemberId();
        this.sellerName = chatRoom.getSeller().getMemberName();
        this.buyerId = chatRoom.getBuyer().getMemberId();
        this.buyerName = chatRoom.getBuyer().getMemberName();
        this.postId = chatRoom.getPost().getPostId();
    }
}
