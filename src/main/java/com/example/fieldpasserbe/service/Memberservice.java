package com.example.fieldpasserbe.service;

import com.example.fieldpasserbe.dto.MemberDTO;
import com.example.fieldpasserbe.dto.MemberInfo;
import com.example.fieldpasserbe.dto.MemberUpdate;
import com.example.fieldpasserbe.entity.MemberEntity;

import java.util.Optional;

public interface Memberservice {

    String LoginMember(String email,String password); // 로그인

    String Signup(MemberDTO memberDTO);// 회원가입


    Optional<MemberEntity> selectMember(MemberInfo memberInfo);

    String updateMember(MemberUpdate memberupdate);

    String deleteMember(MemberDTO memberDTO);

    String updatePassword(MemberDTO memberDTO);

    Integer findByEmail(String email);

    boolean userEmailCheck(String email);
}
