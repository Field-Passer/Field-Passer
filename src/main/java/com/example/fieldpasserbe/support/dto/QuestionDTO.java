package com.example.fieldpasserbe.support.dto;


import com.example.fieldpasserbe.support.entity.Question;
import com.example.fieldpasserbe.support.entity.QuestionProccess;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class QuestionDTO {

    private int questionId;

    private int memberId;

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime registerDate;

    private QuestionProccess questionProccess;


    public QuestionDTO(Question question){
        this.questionId = question.getQuestionId();
        this.memberId = question.getMember().getMemberId();
        this.title = question.getTitle();
        this.registerDate = question.getRegisterDate();
        this.questionProccess = question.getQuestionProccess();

    }

}
