package com.example.fieldpasserbe.chat.dto;

import com.example.fieldpasserbe.chat.entity.ChatRoom;

import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.post.entity.Post;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatRoomRequestDto {

    private int sellerId;
    private int buyerId;
    private int postId;

    public ChatRoom toEntity() {
        return ChatRoom.builder()
                .seller(Member.builder().memberId(sellerId).build())
                .buyer(Member.builder().memberId(buyerId).build())
                .post(Post.builder().postId(postId).build())
                .build();
    }

}
