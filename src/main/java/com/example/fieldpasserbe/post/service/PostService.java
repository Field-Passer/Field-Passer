package com.example.fieldpasserbe.post.service;

import com.example.fieldpasserbe.admin.dto.BlindRequestDTO;
import com.example.fieldpasserbe.post.dto.PostRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    String insertPost(MultipartFile file, PostRequestDto postRequestDto);
    String editPost(int postId, MultipartFile file, PostRequestDto postRequestDto);
    String deletePost(int postId);
    void deleteOverTime();

    int blindPostByID(int postId, boolean flag);
}
