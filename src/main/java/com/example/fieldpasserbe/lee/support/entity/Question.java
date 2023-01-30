package com.example.fieldpasserbe.lee.support.entity;

import com.example.fieldpasserbe.lee.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "QUESTION")
public class Question {

    @Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    private int questionId;

    @ManyToOne
    @JoinColumn(name = "ID")
    private Member member;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private QuestionCategory questionCategory;

    @Column(name = "REGISTER_DATE")
    private LocalDateTime registerDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "PROCCESS")
    @Enumerated(EnumType.STRING)
    private QuestionProccess questionProccess;
}
