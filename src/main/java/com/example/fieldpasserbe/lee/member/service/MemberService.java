package com.example.fieldpasserbe.lee.member.service;

import com.example.fieldpasserbe.lee.member.entity.Member;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findMemberById(int id);

    Optional<Member> findMemberByEmail(String email) throws NullPointerException;

    Optional<Member> findAdminByEmail(String email) throws NullPointerException;

    boolean updateVisitCount(int id);

    Page<Member> findAllMembers(int page);
}
