package com.example.fieldpasserbe.admin.service.impl;

import com.example.fieldpasserbe.admin.dto.MemberDTO;
import com.example.fieldpasserbe.admin.service.AdminService;
import com.example.fieldpasserbe.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.admin.dto.AdminLoginResponceDTO;
import com.example.fieldpasserbe.admin.dto.MemberListDTO;
import com.example.fieldpasserbe.admin.entity.Admin;
import com.example.fieldpasserbe.admin.repository.AdminRepositoryJPA;
import com.example.fieldpasserbe.admin.vo.AdminLoginVO;
import com.example.fieldpasserbe.admin.vo.MemberListVO;
import com.example.fieldpasserbe.admin.vo.MemberVO;
import com.example.fieldpasserbe.admin.vo.SimpleVO;
import com.example.fieldpasserbe.post.service.PostService;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.service.MemberService;
import com.example.fieldpasserbe.support.dto.PunishDTO;
import com.example.fieldpasserbe.support.service.PunishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final MemberService memberService;
    private final PostService postService;
    private final PunishService punishService;
    private final AdminRepositoryJPA adminRepository;


    /**
     * 전체 회원 조회
     * @return
     * @throws Exception
     */
    @Override
    public MemberListVO lookUpmembers(int page) throws Exception{
        try {
            Page<Member> members = memberService.findAllMembers(page);
            List<MemberListDTO> resultData = new ArrayList<>();
            for (Member member : members.getContent()) {
                resultData.add(MemberListDTO.builder()
                                .memberId(member.getMemberId())
                                .email(member.getEmail())
                                .memberName(member.getMemberName())
                                .signupDate(member.getSignUpDate())
                                .postCount(postService.countPostById(member.getMemberId()))  //글 개수
                                .visitCount(member.getVisitCount())
                                .privilege(member.convertPrivilege())
                                .reportNum(punishService.countBytargetId(member.getMemberId()))  //신고 수
                                .authority(member.convertAuthority())
                        .build());
            }
            System.out.println("members.getTotalPages() = " + members.getTotalPages());
            System.out.println("members.getNumber() = " + members.getNumber());
            return MemberListVO.builder()
                    .resultCode("success")
                    .resultDataNum(members.getTotalElements())
                    .resultData(resultData)
                    .currentPage(members.getNumber())
                    .totalPage(members.getTotalPages())
                    .sort(members.getSort())
                    .build();
        } catch (NullPointerException e) {
            throw new Exception("failed : 조회할 수 있는 회원이 없습니다.");
        }
    }

    /**
     * 회원 번호로 회원 상세 정보 조회
     * @param memberId
     * @return
     */
    @Override
    public MemberVO memberDetail(int memberId) {
        try {
            Member member = memberService.findMemberById(memberId).get();
            PunishDTO punishDTO = punishService.checkPunish(member.getMemberId());
            return MemberVO.builder()
                    .resultCode("success")
                    .resultData(MemberDTO.builder()
                            .memberId(member.getMemberId())
                            .email(member.getEmail())
                            .memberName(member.getMemberName())
                            .profileImg(member.getProfileImg())
                            .signUpDate(member.getSignUpDate())
                            .visitCount(member.getVisitCount())
                            .postCount(postService.countPostById(member.getMemberId()))
                            .privilege(member.convertPrivilege())
                            .authority(member.convertAuthority())
                            .punishDTO(punishDTO)
                            .build())
                    .build();
        } catch (NullPointerException e) {
            return MemberVO.builder()
                    .resultCode("failed : 조회할 수 있는 회원이 없습니다.")
                    .build();
        }
    }


}
