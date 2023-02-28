package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.member.dto.MemberIdDTO;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.post.dto.ViewMyPostDTO;
import com.example.fieldpasserbe.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ViewMyPostRepositoryJPA extends JpaRepository<Post,Integer> {


    List<Post> findByMember(Member member);

}
