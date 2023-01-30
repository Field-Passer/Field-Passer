package com.example.fieldpasserbe.lee.member.service.impl;

import com.example.fieldpasserbe.lee.member.entity.Member;
import com.example.fieldpasserbe.lee.member.repository.MemberRepositoryJPA;
import com.example.fieldpasserbe.lee.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Optional<Member> findMemberByEmail(String email) throws NullPointerException{
        if (!memberRepository.findMemberByEmail(email).isPresent()) {
            throw new NullPointerException("존재하지 않는 회원입니다.");
        } else {
            return memberRepository.findMemberByEmail(email);
        }
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

    @Override
    public List<Member> findAllMembers() throws NullPointerException{
        if (memberRepository.findAllMembers().isEmpty()) {
            throw new NullPointerException("조회할 수 있는 회원이 없습니다.");
        } else {
            return memberRepository.findAllMembers();
        }
    }
}
