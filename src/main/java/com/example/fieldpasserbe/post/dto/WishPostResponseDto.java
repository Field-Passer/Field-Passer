package com.example.fieldpasserbe.post.dto;

import com.example.fieldpasserbe.post.entity.WishPost;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WishPostResponseDto {
    private int postId;
    private String title;

    public WishPostResponseDto(WishPost wishPost) {
        this.postId = wishPost.getPost().getPostId();
        this.title = wishPost.getPost().getTitle();
    }
}
