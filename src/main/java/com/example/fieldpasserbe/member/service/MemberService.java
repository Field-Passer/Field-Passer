package com.example.fieldpasserbe.member.service;

import com.example.fieldpasserbe.admin.dto.PeriodResponseDTO;
import com.example.fieldpasserbe.dto.MemberDTO;
import com.example.fieldpasserbe.dto.MemberInfo;
import com.example.fieldpasserbe.entity.MemberEntity;
import com.example.fieldpasserbe.member.entity.Member;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findMemberById(int id) throws NullPointerException;

    Optional<Member> findMemberByEmail(String email) throws NullPointerException;

    Optional<Member> findAdminByEmail(String email) throws NullPointerException;

    boolean updateVisitCount(int id);

    Page<Member> findAllMembers(int page);

    List<PeriodResponseDTO> checkNewMember(String startDate, String endDate) throws Exception;

    String LoginMember(String email,String password); // 로그인

    String Signup(MemberDTO memberDTO);// 회원가입

    Optional<Member> selectMember(MemberInfo memberInfo); // 회원정보 조회





}
