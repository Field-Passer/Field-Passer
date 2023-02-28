package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ViewMyPostRepositoryJPA extends JpaRepository<Post,Integer> {


    List<Post> findByMember(Member member);

}
