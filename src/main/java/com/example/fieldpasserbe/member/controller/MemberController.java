package com.example.fieldpasserbe.member.controller;





import com.example.fieldpasserbe.member.dto.MemberDTO;
import com.example.fieldpasserbe.member.dto.MemberInfo;
import com.example.fieldpasserbe.member.dto.MemberUpdate;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.service.EmailService;
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

    private final EmailService emailService;

    private final HttpSession session;

    //로그인
    @PostMapping("/api/auth/login")
    public String login(String email, String password ){


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

    // 로그아웃
    @PostMapping("/api/auth/logout")
    public String logout( ){
        System.out.println("email"+session.getAttribute("email"));

        if(session.getAttribute("email")!= null){
            session.setAttribute("email",null);
            return "success";
        }else{
            return " failed";
        }
    }

    //회원가입
    @PostMapping("/api/auth/register")
    public String Signup(@ModelAttribute @Validated MemberDTO memberdto){

        return memberService.Signup(memberdto);
    }

    // 회원 정보 조회
    @GetMapping("/api/:memberid")
    public MemberDTO selectMember( ){
       Integer memberId = (int)session.getAttribute("id");

        return memberService.selectMember(memberId);
    }

    //회원 정보 수정
    @PatchMapping("/api/:userid/userinfo")
    public String updateMember(MemberUpdate memberUpdate){
        Integer memberId = (int)session.getAttribute("id");
        return memberService.updateMember(memberId,memberUpdate);
    }


    //회원 탈퇴
    @PatchMapping("/api/:userid/unregister")
    public String deleteMember(MemberDTO memberDTO){
        Integer memberId = (int)session.getAttribute("id");
        return memberService.deleteMember(memberDTO,memberId);
    }

    // 비밀번호 변경
    @PatchMapping("/api/:userid/userpwd")
    public String updatePassword(MemberDTO memberDTO){
        Integer memberId = (int)session.getAttribute("id");
        return memberService.updatePassword(memberDTO,memberId);
    }


    // 이메일 인증
    @PostMapping("/emailConfirm")
    @ResponseBody
    public String emailConfirm(@RequestParam String email) throws Exception {

        String confirm = emailService.sendSimpleMessage(email);

        return confirm;
    }



}
