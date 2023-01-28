package com.example.fieldpasserbe.member.repository;

import com.example.fieldpasserbe.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepositoryJPA extends JpaRepository<Member, Integer> {

    //id로 유저 찾기
    Optional<Member> findMemberById(int id);

    //이메일로 유저 찾기
    Optional<Member> findMemberByEmail(String email);


}