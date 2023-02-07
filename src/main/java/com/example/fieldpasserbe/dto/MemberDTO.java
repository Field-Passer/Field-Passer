package com.example.fieldpasserbe.dto;

import com.example.fieldpasserbe.entity.MemberEntity;
import com.example.fieldpasserbe.member.entity.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

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


}
