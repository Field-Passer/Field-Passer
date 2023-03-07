package com.example.fieldpasserbe.support.repository;

import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.post.entity.Post;
import com.example.fieldpasserbe.support.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepositoryJPA extends JpaRepository<Question,Integer> {


    List<Question> findByMember(Member member);
}
