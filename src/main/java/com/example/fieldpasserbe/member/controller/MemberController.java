package com.example.fieldpasserbe.member.controller;


import com.example.fieldpasserbe.dto.MemberDTO;
import com.example.fieldpasserbe.service.Memberservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
}
