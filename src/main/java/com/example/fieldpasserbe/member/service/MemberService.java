package com.example.fieldpasserbe.member.service;

import com.example.fieldpasserbe.admin.dto.PeriodMemberResponseDTO;
import com.example.fieldpasserbe.global.response.ResponseDTO;
import com.example.fieldpasserbe.member.dto.MemberDTO;
import com.example.fieldpasserbe.member.dto.MemberUpdate;
import com.example.fieldpasserbe.member.entity.Member;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findMemberById(int id) throws NullPointerException;

    Optional<Member> findMemberByEmail(String email) throws NullPointerException;

    Optional<Member> findAdminByEmail(String email) throws NullPointerException;

    boolean updateVisitCount(int id);

    Page<Member> findAllMembers(int page);

    Page<PeriodMemberResponseDTO> checkNewMember(String startDate, String endDate, int page) throws Exception;

    ResponseDTO<?> LoginMember(String email, String password); // 로그인

    Integer findByEmail(String email); // session id 저장용

    ResponseDTO<?> signUp(MemberDTO memberDTO);// 회원가입

    ResponseDTO<?> selectMember(int memberId); // 회원정보 조회

    String updateMember(int memberId, MemberUpdate memberUpdate); // 회원 정보 수정

    String deleteMember(MemberDTO memberDTO,int memberId); // 회원 탈퇴

    String updatePassword(MemberDTO memberDTO,int memberId);

}
