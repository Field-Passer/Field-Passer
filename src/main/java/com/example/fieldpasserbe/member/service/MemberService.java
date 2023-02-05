package com.example.fieldpasserbe.member.service;

import com.example.fieldpasserbe.admin.dto.PeriodResponceDTO;
import com.example.fieldpasserbe.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findMemberById(int id) throws NullPointerException;

    Optional<Member> findMemberByEmail(String email) throws NullPointerException;

    Optional<Member> findAdminByEmail(String email) throws NullPointerException;

    boolean updateVisitCount(int id);

    Page<Member> findAllMembers(int page);

    List<PeriodResponceDTO> checkNewMember(String startDate, String endDate) throws Exception;
}
