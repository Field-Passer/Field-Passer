package com.example.fieldpasserbe.admin.service.impl;

import com.example.fieldpasserbe.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.admin.dto.AdminLoginResponceDTO;
import com.example.fieldpasserbe.admin.service.AdminService;
import com.example.fieldpasserbe.admin.vo.AdminLoginVO;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private MemberService memberService;


    @Override
    public AdminLoginVO adminLogin(AdminLoginRequestDTO admin, HttpSession session) throws Exception{

        if (!isValidEmail(admin.getEmail())) {
            throw new Exception("failed : 입력이 이메일 형식이 아닙니다.");
        }

        try {
            Member member = memberService.findAdminByEmail(admin.getEmail()).get();
            if (isValidPassword(admin, member)) {
                session.setAttribute("email", member.getEmail());
                memberService.updateVisitCount(member.getId());
                return AdminLoginVO.builder()
                        .resultCode("success")
                        .resultData(AdminLoginResponceDTO.builder()
                                .email(member.getEmail())
                                .profileImg(member.getProfileImg())
                                .memberName(member.getMemberName())
                                .build())
                        .build();
            } else {
                throw new Exception("failed : 비밀번호가 틀렸습니다.");
            }
        } catch (NullPointerException e) {
            throw new Exception("failed : 존재하지 않는 회원입니다.");
        }
    }

    private boolean isValidPassword(AdminLoginRequestDTO admin, Member member) {
        if (member.getPassword().equals(admin.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 이메일 형식 유효성 검사
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
