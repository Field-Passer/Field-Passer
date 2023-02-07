package com.example.fieldpasserbe.member.controller;





import com.example.fieldpasserbe.member.dto.MemberDTO;
import com.example.fieldpasserbe.member.dto.MemberInfo;
import com.example.fieldpasserbe.member.dto.MemberUpdate;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
public class MemberController {

    private final MemberService memberService;

    //로그인
    @PostMapping("/api/auth/login")
    public String login(String email, HttpSession session, String password ){


        String result = memberService.LoginMember(email,password);
        Integer id = memberService.findByEmail(email);
        System.out.println("result=" +result);
        if(result.equals("success")){
            session.setAttribute("email",email);
            System.out.println("email" + session.getAttribute("email"));
            session.setAttribute("id",id);

        }

        return result;
    }

    //회원가입
    @PostMapping("/api/auth/register")
    public String Signup(@ModelAttribute @Validated MemberDTO memberdto){
        System.out.println();
        return memberService.Signup(memberdto);
    }

    // 회원 정보 조회
    @GetMapping("/api/:memberid")
    public Optional<Member> selectMember(MemberInfo memberinfo){
        return memberService.selectMember(memberinfo);
    }

    //회원 정보 수정
    @PatchMapping("/api/:userid/userinfo")
    public String updateMember(MemberUpdate memberupdate){
        return memberService.updateMember(memberupdate);
    }


    //회원 탈퇴
    @PatchMapping("/api/:userid/unregister")
    public String deleteMember(MemberDTO memberDTO){
        return memberService.deleteMember(memberDTO);
    }

    // 비밀번호 변경
    @PatchMapping("/api/:userid/userpwd")
    public String updatePassword(MemberDTO memberDTO){
        return memberService.updatePassword(memberDTO);
    }




}
