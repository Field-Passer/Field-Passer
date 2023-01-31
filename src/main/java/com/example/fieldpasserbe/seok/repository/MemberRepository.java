package com.example.fieldpasserbe.seok.repository;

import com.example.fieldpasserbe.seok.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
