package com.example.fieldpasserbe.member.dto;


import com.example.fieldpasserbe.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberUpdatePassword {

    private int memberId;

    private String password;


    public MemberUpdatePassword(Member member){

        this.password = member.getPassword();
    }
}
