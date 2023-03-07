package com.example.fieldpasserbe.post.service.impl;

import com.example.fieldpasserbe.global.response.ErrorResponseDTO;
import com.example.fieldpasserbe.global.response.ResponseDTO;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.repository.MemberRepositoryJPA;
import com.example.fieldpasserbe.post.dto.ViewMyPostDTO;
import com.example.fieldpasserbe.post.entity.Post;
import com.example.fieldpasserbe.post.repository.ViewMyPostRepositoryJPA;
import com.example.fieldpasserbe.post.service.ViewMyPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ViewMyPostServiceImpl implements ViewMyPostService {

    private final MemberRepositoryJPA memberRepository;

    private final ViewMyPostRepositoryJPA viewMyPostRepository;




    @Override
    public ResponseDTO<?> selectMyPost(int Id) {

     try{

         Member member = memberRepository.findById(Id).get();

         List<Post> postList = viewMyPostRepository.findByMember(member);
         List<ViewMyPostDTO> list = postList.stream()
                 .map(ViewMyPostDTO ::new)
                 .collect(Collectors.toList());

         return new ResponseDTO<>(list);
     }catch (Exception e) {
         e.printStackTrace();
         return new ErrorResponseDTO(500,"글을 불러 올 수 없습니다").toResponse();
     }

    }
}
