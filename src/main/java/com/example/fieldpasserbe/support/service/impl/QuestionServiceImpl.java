package com.example.fieldpasserbe.support.service.impl;

import com.example.fieldpasserbe.global.response.ErrorResponseDTO;
import com.example.fieldpasserbe.global.response.ResponseDTO;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.repository.MemberRepositoryJPA;
import com.example.fieldpasserbe.support.dto.QuestionDTO;
import com.example.fieldpasserbe.support.entity.Question;
import com.example.fieldpasserbe.support.repository.QuestionRepositoryJPA;
import com.example.fieldpasserbe.support.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final MemberRepositoryJPA memberRepositoryJPA;

    private final QuestionRepositoryJPA questionRepositoryJPA;


    @Override
    public ResponseDTO<?> selectMyinquiry(int memberId) {


        Member member = memberRepositoryJPA.findById(memberId).get();

        System.out.println("Member="+member.getMemberId());
        try{

            List<Question> questionList = questionRepositoryJPA.findByMember(member);

            System.out.println("question"+questionList.size());

            List<QuestionDTO> dtoList =  questionList.stream()
                    .map(QuestionDTO :: new)
                    .collect(Collectors.toList());

            System.out.println("question" + dtoList.size());
            System.out.println("question" + dtoList);

            return new ResponseDTO<>(dtoList);
        }catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponseDTO(500,"내 글을 불러 올 수 없습니다.").toResponse();
        }




    }
}
