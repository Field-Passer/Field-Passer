package com.example.fieldpasserbe.member.dto;

import com.example.fieldpasserbe.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {

    private int memberId;
    private String password;

    @Email(message= "올바른 이메일 주소를 입력해수제요.")
    private String email;


    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    private String memberName;
    private String profileImg;

    private byte privilege;
    private byte authority;

    private int visitCount;

    private byte delete;
    private LocalDateTime signUpDate;



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
                .signUpDate(this.signUpDate)
                .delete(this.delete)
                .build();

    }

    public MemberDTO(Member member){
        this.email=member.getEmail();
        this.memberName = member.getMemberName();
        this.profileImg = member.getProfileImg();
        this.signUpDate = member.getSignUpDate();
    }


}