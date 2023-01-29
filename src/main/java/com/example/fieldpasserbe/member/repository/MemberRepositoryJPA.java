package com.example.fieldpasserbe.member.repository;

import com.example.fieldpasserbe.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryJPA extends JpaRepository<Member, Integer> {

    //id로 유저 찾기
    Optional<Member> findMemberById(int id);

    //이메일로 유저 찾기
    Optional<Member> findMemberByEmail(String email);

    //이메일로 관리자 찾기
    @Query("select m from Member m where m.privilege = 1 AND m.email = :email")
    Optional<Member> findAdminByEmail(@Param("email") String email);

    //유저 방문 횟수 증가
    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.visitCount = m.visitCount + 1 where m.id = :id")
    int updateVisitCount(@Param("id") int id);

    //전체 회원 조회
    @Query("select m from Member m where m.delete = 0")
    List<Member> findAllMembers();
}