package com.example.fieldpasserbe.post.dto;

import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.post.entity.Stadium;
import com.example.fieldpasserbe.post.entity.WishField;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WishFieldRequest {
    private int memberId;
    private int stadiumId;

    public WishField toEntity() {
        return WishField.builder()
                .member(Member.builder().memberId(memberId).build())
                .stadium(Stadium.builder().stadiumId(stadiumId).build())
                .build();
    }
}
