package com.example.fieldpasserbe.post.entity;

import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.post.dto.WishFieldId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(WishFieldId.class)
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Wish_Field")
public class WishField {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STADIUM_ID")
    private Stadium stadium;
}
