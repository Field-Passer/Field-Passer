package com.example.fieldpasserbe.repository;

import com.example.fieldpasserbe.dto.Login;
import com.example.fieldpasserbe.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {

    Optional<MemberEntity> findByEmailAndPassword(String email, String password);// 로그인

    MemberEntity findByEmail(String email);
    //List<MemberEntity> findByEmail(@Param("email") String email);

   // Optional<MemberEntity> findByEmail(String email);

}
