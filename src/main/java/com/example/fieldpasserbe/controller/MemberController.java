package com.example.fieldpasserbe.controller;

import com.example.fieldpasserbe.dto.MemberDTO;

import com.example.fieldpasserbe.dto.MemberInfo;
import com.example.fieldpasserbe.dto.MemberUpdate;
import com.example.fieldpasserbe.entity.MemberEntity;
import com.example.fieldpasserbe.service.EmailService;
import com.example.fieldpasserbe.service.Memberservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
public class MemberController {

    private final Memberservice memberService;

    private final EmailService emailService;

    //회원가입
    @PostMapping("/api/auth/register")
    public String Signup(@ModelAttribute @Validated MemberDTO memberdto){
        System.out.println();
        return memberService.Signup(memberdto);
    }


    //로그인
    @PostMapping("/api/auth/login")
    public String login(String email, HttpSession session,String password ){


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

    //세션 id값 저장
//    @PostMapping("/api/auth/login")
//    public String login(String email, HttpSession session,String password ){
//
//
//        String result = memberService.LoginMember(email,password);
//        if(result.equals("success")){
//            session.setAttribute("email",email);
//
//        }
//
//        return result;
//    }



    //로그아웃
    @PostMapping("/api/auth/logout")
    public String logout(HttpSession session){
        System.out.println("email"+session.getAttribute("email"));

        if(session.getAttribute("email")!= null){
            session.setAttribute("email",null);
            return "success";
        }else{
            return " failed";
        }
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

    // 이메일 인증
    @PostMapping("/emailConfirm")
    @ResponseBody
    public String emailConfirm(@RequestParam String email) throws Exception {

        String confirm = emailService.sendSimpleMessage(email);

        return confirm;
    }



//    @GetMapping("/api/auth/findpw")
//    public boolean checkEmail(@RequestParam("memberEmail") String memberEamil){
//        log.info("checkEmail 진입");
//        return
//    }
}
