package com.example.fieldpasserbe.member.controller;


import com.example.fieldpasserbe.dto.MemberDTO;
import com.example.fieldpasserbe.dto.MemberInfo;
import com.example.fieldpasserbe.dto.MemberUpdate;
import com.example.fieldpasserbe.entity.MemberEntity;
import com.example.fieldpasserbe.service.Memberservice;
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

    private final Memberservice memberService;

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
    public Optional<MemberEntity> selectMember(MemberInfo memberinfo){
        return memberService.selectMember(memberinfo);
    }

    //회원 정보 수정
    @PatchMapping("/api/:userid/userinfo")
    public String updateMember(MemberUpdate memberupdate){
        return memberService.updateMember(memberupdate);
    }


}
