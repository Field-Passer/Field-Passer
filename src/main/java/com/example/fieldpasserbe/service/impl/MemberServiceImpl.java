package com.example.fieldpasserbe.service.impl;

import com.example.fieldpasserbe.dto.MemberDTO;
import com.example.fieldpasserbe.dto.MemberInfo;
import com.example.fieldpasserbe.dto.MemberUpdate;
import com.example.fieldpasserbe.entity.MemberEntity;
import com.example.fieldpasserbe.repository.MemberRepository;
import com.example.fieldpasserbe.service.Memberservice;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberServiceImpl implements Memberservice {

    private final MemberRepository mr;
    private final PasswordEncoder bCryptPasswordEncoder;

    private final HttpSession session;

    //로그인
    @Override
    public String LoginMember(String email,String password) {

//        MemberEntity me = mr.findByEmailAndPassword(login.getEmail(),login.getPassword())
//                .orElse(null);
        MemberEntity me = mr.findByEmail(email);

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

//        if(me != null){
//            return "success";
//        }else{
//
//            return "failed";
//        }

    }






    //회원가입
    @Override
    public String Signup(MemberDTO memberDTO) {


        try{
//             String password = memberDTO.getPassword();
//             String newPassword =  bCryptPasswordEncoder.encode(password);
//             memberDTO.setPassword(newPassword);
            MemberEntity newMember = memberDTO.toEntity();
            newMember.hashPassword(bCryptPasswordEncoder);

            mr.save(newMember);
        }catch(Exception e){
            e.printStackTrace();

            return "failed";
        }

        return "success";
    }


    // 회원 정보 조회
    public Optional<MemberEntity> selectMember(MemberInfo memberinfo) throws NullPointerException{
        Integer id = (int)session.getAttribute("id");


          Optional<MemberEntity> memberEntity =  mr.findById(id);
//        if(result.size()==1){
//
//         List<MemberInfo> infoList = me.stream()
//                    .map(info -> new MemberInfo(memberinfo.toEntity()))
//                    .collect(Collectors.toList());
//
//            return infoList.get(0);
//        }else{
//            return null;
//        }
//        return null;
        if(memberEntity.isPresent()){
            return memberEntity;
        }else{
            throw new NullPointerException(" 존재하지 않는 회원 입니다 ");
        }
    }


    //회원 정보 수정
    @Override
    public String updateMember(MemberUpdate memberupdate) {
        Integer id = (int)session.getAttribute("id");

        Optional<MemberEntity> me = mr.findById(id);

       if(me != null){
           me.ifPresent(updatemember ->{
               updatemember.setMemberName(memberupdate.getMemberName());
               updatemember.setEmail(memberupdate.getEmail());
               updatemember.setProfile_img(memberupdate.getProfile_img());
               updatemember.setPassword(memberupdate.getPassword());

               mr.save(updatemember);
           });
           return "success";
       }

           return "failed";
    }


    //회원 삭제
    @Override
    public String deleteMember(MemberDTO memberDTO) {

        Optional<MemberEntity> me =mr.findById(memberDTO.getId());

        if(me != null ){
            me.ifPresent(deleteMember -> {
                deleteMember.setDelete(memberDTO.getDelete());

                mr.save(deleteMember);
            });
            return " success";
        }

        return "failed";
    }

    // 비밀번호 변경
    @Override
    public String updatePassword(MemberDTO memberDTO) {
        Optional<MemberEntity> me =mr.findById(memberDTO.getId());

        if(me != null){
            me.ifPresent(updatePassword -> {
                updatePassword.setPassword(memberDTO.getPassword());

                mr.save(updatePassword);
            });
            return "success";
        }
        return "failed";
    }

    @Override
    public Integer findByEmail(String email) {

        MemberEntity memberEmail = mr.findByEmail(email);
        return memberEmail.getId();
    }

    @Override
    public boolean userEmailCheck(String email){
        MemberEntity member = mr.findByEmail(email);
        if(member != null){
            return true;
        }else{
            return false;
        }
    }

/**
 * 이메일 중복 여부를 확인
 *
 * @param email
 * @return true | false
 */
//    private boolean isEmailExist(String email){
//        List<MemberEntity> byEmail = mr.findByEmail(email);
//        if(byEmail.size() ==1){
//
//            return !byEmail.isEmpty();
//        }else{
//            return true;
//        }
//    }


}
