package com.example.fieldpasserbe.post.entity;

import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.post.dto.WishPostId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(WishPostId.class)
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Wish_Post")
public class WishPost {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;
}
