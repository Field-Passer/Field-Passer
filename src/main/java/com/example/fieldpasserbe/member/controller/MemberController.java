package com.example.fieldpasserbe.member.controller;





import com.example.fieldpasserbe.global.response.ErrorResponseDTO;
import com.example.fieldpasserbe.global.response.ResponseDTO;
import com.example.fieldpasserbe.member.dto.MemberDTO;

import com.example.fieldpasserbe.member.dto.MemberUpdate;

import com.example.fieldpasserbe.member.dto.MemberUpdatePassword;
import com.example.fieldpasserbe.member.service.MailService;
import com.example.fieldpasserbe.member.service.MemberService;
import com.example.fieldpasserbe.member.vo.MailVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@Slf4j
@Transactional
public class MemberController {

    private final MemberService memberService;

    private final MailService mailService;



    private final HttpSession session;

    //로그인
    @PostMapping("/api/auth/login")
    public ResponseDTO<?> login(String email, String password ){

        return memberService.LoginMember(email,password);
    }

    // 로그아웃
    @PostMapping("/api/auth/logout")
    public ResponseDTO<?> logout( ){

        if(session.getAttribute("email")!= null){
            session.setAttribute("email",null);
            return new ResponseDTO<>(null);
        }else{
            return new ErrorResponseDTO(500,"로그아웃을 하지 못 했습니다").toResponse();
        }
    }

    //회원가입
    @PostMapping("/api/auth/register")
    public ResponseDTO<?> Signup(@ModelAttribute @Validated MemberDTO memberdto,@RequestParam("profileImg") MultipartFile profileImg){

        return memberService.signUp(memberdto,profileImg);
    }

    // 회원 정보 조회
    @GetMapping("/api/:memberid")
    public ResponseDTO<?> selectMember( ){
       Integer memberId = (int)session.getAttribute("id");

        return memberService.selectMember(memberId);
    }

    //회원 정보 수정
    @PatchMapping("/api/:userid/userinfo")
    public ResponseDTO<?> updateMember(MemberUpdate memberUpdate, @RequestParam("profileImg") MultipartFile profileImg){
        Integer memberId = (int)session.getAttribute("id");
        return memberService.updateMember(memberId,memberUpdate,profileImg);
    }


    //회원 탈퇴
    @PatchMapping("/api/:userid/unregister")
    public ResponseDTO<?> deleteMember(){
        Integer memberId = (int)session.getAttribute("id");
        return memberService.deleteMember(memberId);
    }

    // 비밀번호 변경
    @PatchMapping("/api/:userid/userpwd")
    public ResponseDTO<?> updatePassword(MemberUpdatePassword memberUpdatePassword){
        Integer memberId = (int)session.getAttribute("id");
        return memberService.updatePassword(memberUpdatePassword,memberId);
    }


    /**이메일이 DB에 존재하는지 확인 **/
    @GetMapping("/checkEmail")
    public ResponseDTO<?> checkEmail(@RequestParam("memberEmail") String memberEmail){

        log.info("checkEmail 진입");
        return memberService.checkEmail(memberEmail);
    }

    /** 비밀번호 찾기 - 임시 비밀번호 발급 **/

    @PostMapping("/sendPwd")
    public String sendPwdEmail(@RequestParam("memberEmail") String memberEmail) {

        try{

            log.info("sendPwdEmail 진입");
            log.info("이메일 : "+ memberEmail);

            /** 임시 비밀번호 생성 **/
            String tmpPassword = memberService.getTmpPassword();

            /** 임시 비밀번호 저장 **/
            memberService.updatePasswordMail(tmpPassword, memberEmail);

            /** 메일 생성 & 전송 **/
            MailVo mail = mailService.createMail(tmpPassword, memberEmail);
            mailService.sendMail(mail);

            log.info("임시 비밀번호 전송 완료");

            return "success";
        }catch (Exception e) {
            return "failed";
        }
    }


    /** 이메일 중복검사 **/
    @GetMapping("/email-duplicateTest")
    public ResponseDTO<?> checkEmailDuplicate(@RequestParam("Email")String Email){
        return memberService.checkEmailDuplicate(Email);

    }
    
    /** memberName 중복 검사 **/
    @GetMapping("/memberName-duplicateTest")
    public ResponseDTO<?> checkMemberNameDuplicate(@RequestParam("memberName") String memberName){
        return memberService.checkMemberNameDuplicate(memberName);
    }


}
