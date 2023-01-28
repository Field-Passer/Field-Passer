package com.example.fieldpasserbe.member.repository;

import com.example.fieldpasserbe.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepositoryJPA extends JpaRepository<Member, Integer> {

    Optional<Member> findMemberById(int id);

    Optional<Member> findMemberByEmail(String email);

}