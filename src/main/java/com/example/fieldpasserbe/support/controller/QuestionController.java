package com.example.fieldpasserbe.support.controller;


import com.example.fieldpasserbe.global.response.ResponseDTO;
import com.example.fieldpasserbe.support.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final HttpSession session;

    private final QuestionService questionService;
    @GetMapping("/api/report/:memberId")
    public ResponseDTO<?> selectMyinquiry(){
        Integer Id = (int)session.getAttribute("id");
        return questionService.selectMyinquiry(Id);
    }
}
