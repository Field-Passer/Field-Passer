package com.example.fieldpasserbe.member.service.impl;

import com.example.fieldpasserbe.admin.dto.PeriodResponseDTO;
import com.example.fieldpasserbe.dto.MemberDTO;
import com.example.fieldpasserbe.entity.MemberEntity;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.repository.MemberRepositoryJPA;
import com.example.fieldpasserbe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final int contentsSize = 10;
    private final MemberRepositoryJPA memberRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원 id로 회원 정보 조회
     * @param id
     * @return
     * @throws NullPointerException
     */
    @Override
    public Optional<Member> findMemberById(int id) throws NullPointerException{
        Optional<Member> member = memberRepository.findMemberByMemberId(id);
        if (member.isPresent()) {
            return member;
        } else {
            throw new NullPointerException("존재하지 않는 회원입니다.");
        }
    }

    /**
     * 이메일로 회원 조회
     * @param email
     * @return
     * @throws NullPointerException
     */
    @Override
    public Optional<Member> findMemberByEmail(String email) throws NullPointerException{
        Optional<Member> member = memberRepository.findMemberByEmail(email);
        if (!member.isPresent()) {
            throw new NullPointerException("존재하지 않는 회원입니다.");
        } else {
            return member;
        }
    }

    /**
     * 이메일로 관리자 조회
     * @param email
     * @return
     * @throws NullPointerException
     */
    @Override
    public Optional<Member> findAdminByEmail(String email) throws NullPointerException{
        Optional<Member> adminByEmail = memberRepository.findAdminByEmail(email);
        if (!adminByEmail.isPresent()) {
            throw new NullPointerException("존재하지 않는 회원입니다.");
        } else {
            return adminByEmail;
        }
    }

    /**
     * 회원의 방문 횟수 증가
     * @param id
     * @return
     */
    @Override
    @Transactional
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

    /**
     * 전체 회원 조회(페이징)
     * @param page
     * @return
     * @throws NullPointerException
     */
    @Override
    public Page<Member> findAllMembers(int page) throws NullPointerException{
        PageRequest pageRequest = PageRequest.of(page - 1, contentsSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Member> allMembers = memberRepository.findAllMembers(pageRequest);
        if (allMembers.getContent().isEmpty()) {
            throw new NullPointerException("조회할 수 있는 회원이 없습니다.");
        } else {
            return allMembers;
        }
    }

    /**
     * 신규 회원 검색
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @Override
    public List<PeriodResponseDTO> checkNewMember(String startDate, String endDate) throws Exception{
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(startDate);
            Date end = format.parse(endDate);
            if (end.getTime() - start.getTime() < 0) {
                throw new IllegalStateException("날짜를 잘못 입력했습니다.");
            }
            List<PeriodResponseDTO> newMember = memberRepository.findNewMember(startDate, endDate);
            if (newMember.size() == 0) {
                throw new NullPointerException("조회할 수 있는 데이터가 없습니다");
            } else {
                return newMember;
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("날짜를 잘못 입력했습니다.");
        }
    }

    //로그인
    @Override
    public String LoginMember(String email,String password) {

        MemberEntity me = memberRepository.findByEmail(email);

        if(me ==null){
            System.out.println("해당 이메일의 유저가 존재하지 않습니다 ");
            return "failed";
        }

        if(!bCryptPasswordEncoder.matches(password, me.getPassword())){
            System.out.println("비밀번호가 일치하지 않습니다 ");
            return "failed";
        }
        System.out.println(email);
        System.out.println(password);
        System.out.println(me.getPassword());
        return "success";

    }

    //회원가입
    @Override
    public String Signup(MemberDTO memberDTO) {


        try{

            Member newMember = memberDTO.toEntity();
            newMember.hashPassword(bCryptPasswordEncoder);

            memberRepository.save(newMember);
        }catch(Exception e){
            e.printStackTrace();

            return "failed";
        }

        return "success";
    }
}
