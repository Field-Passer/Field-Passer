package com.example.fieldpasserbe.member.service.impl;

import com.example.fieldpasserbe.admin.dto.PeriodMemberResponseDTO;
import com.example.fieldpasserbe.global.response.ErrorResponseDTO;
import com.example.fieldpasserbe.global.response.ResponseDTO;
import com.example.fieldpasserbe.member.dto.*;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.repository.MemberRepositoryJPA;
import com.example.fieldpasserbe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;




@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final int contentsSize = 10;
    private final MemberRepositoryJPA memberRepository;


    private final PasswordEncoder bCryptPasswordEncoder;

    private final HttpSession session;

    private final PasswordEncoder passwordEncoder;

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
     *
     * @param startDate
     * @param endDate
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public Page<PeriodMemberResponseDTO> checkNewMember(String startDate, String endDate, int page) throws Exception{
        PageRequest pageRequest = PageRequest.of(page - 1, contentsSize);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(startDate);
            Date end = format.parse(endDate);
            if (end.getTime() - start.getTime() < 0) {
                throw new IllegalStateException("날짜를 잘못 입력했습니다.");
            }
            Page<PeriodMemberResponseDTO> newMember = memberRepository.findNewMember(startDate, endDate, pageRequest);
            if (newMember.getContent().size() == 0) {
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
    public ResponseDTO<?> LoginMember(String email, String password) {

        Member member = memberRepository.findByEmail(email);



        if(member ==null){

            return new ErrorResponseDTO(500,"해당 이메일의 유저가 존재하지 않습니다").toResponse();
        }else if(!bCryptPasswordEncoder.matches(password, member.getPassword())){
            return new ErrorResponseDTO(500,"비밀번호가 일치하지 않습니다").toResponse();

        }else{
            session.setAttribute("email",member.getEmail());
            session.setAttribute("id",member.getMemberId());
            System.out.println("id = "+session.getAttribute("id"));
            System.out.println("email = "+session.getAttribute("email"));

            MemberDTO memberDTO = new MemberDTO(member);

            return new ResponseDTO<>(memberDTO);
        }

    }


    @Override
    public Integer findByEmail(String email) {

        Member memberEmail = memberRepository.findByEmail(email);
        return memberEmail.getMemberId();
    }

    //회원가입
    @Override
    public ResponseDTO<?> signUp(MemberDTO memberDTO) {


        try{

            Member newMember = memberDTO.toEntity();
            newMember.Authority();
            newMember.hashPassword(bCryptPasswordEncoder);


            MemberDTO memberSign = new MemberDTO(memberRepository.save(newMember));
            return new ResponseDTO<>(memberSign);
        }catch(Exception e){
            e.printStackTrace();

            return new ErrorResponseDTO(500,"회원가입을 실패하였습니다.").toResponse();
        }


    }

    // 회원 정보 조회
    public ResponseDTO<?> selectMember(int memberId)throws NullPointerException {
        System.out.println("sessionID =" + session.getAttribute("id"));

        try {



            MemberInfo memberinfo= memberRepository.findMemberByMemberId(memberId).
                    map(member -> new MemberInfo(member)).get();
            System.out.println("memberinfo = "+memberinfo);
            return new ResponseDTO<>(memberinfo);
        } catch (NullPointerException e) {


            return new ErrorResponseDTO(500, "해당 회원을 조회할 수 없습니다").toResponse();
        }


    }


    // 회원 정보 수정
    @Override
    public ResponseDTO<?> updateMember(int memberId , MemberUpdate memberUpdate) {


        Member member = memberRepository.findById(memberId).get();

        if(member != null){

            member.updateMeber(memberUpdate.getEmail(),
                    memberUpdate.getProfileImg(),memberUpdate.getMemberName());

            MemberUpdate memberupdate = new MemberUpdate(member);


            return new ResponseDTO<>(memberupdate);
        }

        return new ErrorResponseDTO(500,"해당 회원을 수정 할 수 없습니다").toResponse();
    }

    //회원 삭제
    @Override
    public ResponseDTO<?> deleteMember(int memberId) {


        Member findMember = memberRepository.findById(memberId).get();

        if(findMember != null){
            findMember.delteMember();

            MemberDelete memberDelete = new MemberDelete(findMember);
            return new ResponseDTO<>(memberDelete);
        }else{
            return new ErrorResponseDTO(500,"회원을 삭제 할 수 없습니다").toResponse();
        }
    }

    // 비밀번호 변경
    @Override
    public ResponseDTO<?> updatePassword(MemberUpdatePassword memberUpdatePassword, int memberId ) {

        Member member =memberRepository.findById(memberId).get();

        if(member != null){
            member.updatePassword(passwordEncoder.encode(memberUpdatePassword.getPassword()));

            MemberUpdatePassword updatePassword = new MemberUpdatePassword(member);
            return new ResponseDTO<>(updatePassword);
        }
        return new ErrorResponseDTO(500,"비밀번호 변경을 할 수 없습니다").toResponse();
    }

    /** 이메일이 존재하는지 확인 **/
    @Override
    public ResponseDTO<?> checkEmail(String memberEmail) {

        /* 이메일이 존재하면 true, 이메일이 없으면 false  */
        Member member = memberRepository.findByEmail(memberEmail);

        if(member.getEmail() == null){
            return new ErrorResponseDTO(500,"이메일을 찾을 수 없습니다").toResponse();
        }

        session.setAttribute("email",member.getEmail());

        System.out.println("email" + session.getAttribute("email"));


        return new ResponseDTO<>(member.getEmail());



    }

    /** 임시 비밀번호 생성 **/
    @Override
    public String getTmpPassword() {
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String pwd = "";

        /* 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 조합 */
        int idx = 0;
        for(int i = 0; i < 10; i++){
            idx = (int) (charSet.length * Math.random());
            pwd += charSet[idx];
        }

        log.info("임시 비밀번호 생성");

        return pwd;
    }

    /** 임시 비밀번호로 업데이트 **/
    @Override
    public void updatePasswordMail(String tmpPassword, String memberEmail) {

        String sessionEmail = (String)session.getAttribute("email");
        System.out.println("Email = " +sessionEmail );

        if(memberEmail == null){
            log.info("해당 이메일이 없습니다 ");
        }else if(memberEmail.equals(sessionEmail)){
            String encryptPassword = bCryptPasswordEncoder.encode(tmpPassword);
            Member member = memberRepository.findByEmail(memberEmail);

            System.out.println("memberemail = " + member.getEmail());
            member.updatePassword(encryptPassword);
            log.info("임시 비밀번호 업데이트");
        }


    }








}
