package com.example.fieldpasserbe.admin.service.impl;

import com.example.fieldpasserbe.admin.dto.*;
import com.example.fieldpasserbe.admin.service.AdminService;
import com.example.fieldpasserbe.admin.repository.AdminRepositoryJPA;
import com.example.fieldpasserbe.admin.vo.*;
import com.example.fieldpasserbe.post.service.PostSearchService;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.service.MemberService;
import com.example.fieldpasserbe.support.dto.PunishDTO;
import com.example.fieldpasserbe.support.service.PunishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final MemberService memberService;
    private final PostSearchService postSearchService;
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
                                .postCount(postSearchService.countPostById(member.getMemberId()))  //글 개수
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
                            .postCount(postSearchService.countPostById(member.getMemberId()))
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

    /**
     * 신규 가입자 기간 검색
     * @param period
     * @return
     */
    @Override
    public PeriodMemberVO checkNewMember(PeriodRequestDTO period) {
        try {
            List<PeriodResponceDTO> newMember = memberService.checkNewMember(period.getStartDate(), period.getEndDate());
            return PeriodMemberVO.builder()
                    .resultCode("success")
                    .resultDataNum(newMember.size())
                    .resultData(newMember)
                    .build();
        } catch (NullPointerException e) {
            return PeriodMemberVO.builder()
                    .resultCode("failed : 조회할 수 있는 데이터가 없습니다.")
                    .build();
        } catch (IllegalStateException e) {
            return PeriodMemberVO.builder()
                    .resultCode("failed : 날짜를 잘못 입력했습니다.")
                    .build();
        } catch (Exception e) {
            return PeriodMemberVO.builder()
                    .resultCode("failed : 뭔가 잘못됐습니다..")
                    .build();
        }
    }


}
