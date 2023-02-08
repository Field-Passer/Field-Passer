package com.example.fieldpasserbe.post.dto;

import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.post.entity.Post;
import com.example.fieldpasserbe.post.entity.WishPost;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WishPostRequest {
    private int memberId;
    private int postId;

    public WishPost toEntity() {
        return WishPost.builder()
                .member(Member.builder().memberId(memberId).build())
                .post(Post.builder().postId(postId).build())
                .build();
    }
}
