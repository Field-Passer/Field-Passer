package com.example.fieldpasserbe.member.dto;

import com.example.fieldpasserbe.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberInfo {


    private int memberId;
    private String email;

    private String memberName;

    private String profileImg;

    public MemberInfo(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.memberName = member.getMemberName();
        this.profileImg = member.getProfileImg();
    }



    public Member toEntity(){
        return Member.builder()
                .memberId(this.memberId)
                .email(this.email)
                .memberName(this.memberName)
                .profileImg(this.profileImg)
                .build();
    }
}
