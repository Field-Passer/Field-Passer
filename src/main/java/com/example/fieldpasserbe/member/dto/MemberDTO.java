package com.example.fieldpasserbe.member.dto;

import com.example.fieldpasserbe.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {

    private int memberId;
    private String password;
    private String email;
    private String memberName;
    private String profileImg;
    private byte privilege;
    private byte authority;

    private int visitCount;

    private byte delete;



    public Member toEntity(){
        return Member.builder()
                .memberId(this.memberId)
                .password(this.password)
                .email(this.email)
                .memberName(this.memberName)
                .profileImg(this.profileImg)
                .privilege(this.privilege)
                .authority(this.authority)
                .visitCount(this.visitCount)
                .delete(this.delete)
                .build();

    }

    public MemberDTO(Member member){
        this.email=member.getEmail();
        this.memberName = member.getMemberName();
        this.profileImg = member.getProfileImg();
    }


}