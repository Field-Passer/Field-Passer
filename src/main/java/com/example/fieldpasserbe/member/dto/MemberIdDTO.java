package com.example.fieldpasserbe.member.dto;

import com.example.fieldpasserbe.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberIdDTO {
    private int memberId;

    public MemberIdDTO(Member member){
        this.memberId = member.getMemberId();
    }
}
