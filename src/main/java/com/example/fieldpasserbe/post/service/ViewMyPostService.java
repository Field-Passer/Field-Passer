package com.example.fieldpasserbe.post.service;

import com.example.fieldpasserbe.global.response.ResponseDTO;
import com.example.fieldpasserbe.post.dto.ViewMyPostDTO;

import java.util.Optional;

public interface ViewMyPostService {

    ResponseDTO<?> selectMyPost(int memberId);
}
