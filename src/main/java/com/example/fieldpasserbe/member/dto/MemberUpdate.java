package com.example.fieldpasserbe.member.dto;


import com.example.fieldpasserbe.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberUpdate {

    private int memberId;
    private String email;
    private String password;
    private String memberName;
    private String image;

//    public Member toEntity(){
//        return Member.builder()
//                .memberId(this.memberId)
//                .email(this.email)
//                .password(this.password)
//                .profileImg(this.image)
//                .memberName(this.memberName)
//                .build();
//    }

    public MemberUpdate(Member member){
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.memberName = member.getMemberName();
        this.image = member.getProfileImg();


    }
}
