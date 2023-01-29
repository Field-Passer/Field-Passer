package com.example.fieldpasserbe.member.service.impl;

import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.repository.MemberRepositoryJPA;
import com.example.fieldpasserbe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {


    @Autowired
    private MemberRepositoryJPA memberRepository;

    @Override
    public Optional<Member> findMemberById(int id) {
        return memberRepository.findMemberById(id);
    }

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email);
    }

    @Override
    public Optional<Member> findAdminByEmail(String email) throws NullPointerException{
        if (!memberRepository.findAdminByEmail(email).isPresent()) {
            throw new NullPointerException("존재하지 않는 회원입니다.");
        } else {
            return memberRepository.findAdminByEmail(email);
        }
    }

    @Override
    public boolean updateVisitCount(int id) {
        try {
            if (memberRepository.updateVisitCount(id) != 1) {
                throw new Exception("방문 카운트 증가에 실패했습니다.");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
