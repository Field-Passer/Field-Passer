package com.example.fieldpasserbe.admin.service.impl;

import com.example.fieldpasserbe.admin.dto.*;
import com.example.fieldpasserbe.admin.service.AdminService;
import com.example.fieldpasserbe.admin.vo.*;
import com.example.fieldpasserbe.post.service.PostSearchService;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.service.MemberService;
import com.example.fieldpasserbe.support.dto.PunishDTO;
import com.example.fieldpasserbe.support.dto.PunishResponseDTO;
import com.example.fieldpasserbe.support.entity.Punish;
import com.example.fieldpasserbe.support.service.PunishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final MemberService memberService;
    private final PostSearchService postSearchService;
    private final PunishService punishService;


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
                    .currentPage(members.getNumber() + 1)
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
    public MemberVO memberDetail(int memberId) throws Exception {
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
            throw new Exception("failed : 조회할 수 있는 회원이 없습니다.");
        }
    }

    /**
     * 신규 가입자 기간 검색
     *
     * @param period
     * @param page
     * @return
     */
    @Override
    public PeriodMemberVO checkNewMember(PeriodRequestDTO period, int page) throws Exception {
        try {
            Page<PeriodMemberResponseDTO> newMember = memberService.checkNewMember(period.getStartDate(), period.getEndDate(), page);
            return PeriodMemberVO.builder()
                    .resultCode("success")
                    .resultDataNum(newMember.getContent().size())
                    .resultData(newMember.getContent())
                    .currentPage(newMember.getNumber() + 1)
                    .totalPage(newMember.getTotalPages())
                    .sort(newMember.getSort())
                    .build();
        } catch (NullPointerException e) {
            throw new Exception("failed : 조회할 수 있는 데이터가 없습니다.");
        } catch (IllegalStateException e) {
            throw new Exception("failed : 날짜를 잘못 입력했습니다.");
        } catch (Exception e) {
            throw new Exception("failed : 뭔가 잘못됐습니다..");
        }
    }

    /**
     * 징계 중인 회원 목록 출력
     * @param page
     * @return
     */
    @Override
    public PunishVO lookUpPunishment(int page) throws Exception {
        try {
            Page<Punish> punishment = punishService.findPunishment(page);
            List<PunishResponseDTO> resultData = new ArrayList<>();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Punish punish : punishment.getContent()) {
                String releaseDate = dateFormat.format(punish.getPunishPeriod().getReleaseDate());
                String judgeDate = dateFormat.format(punish.getPunishPeriod().getJudgeDate());
                resultData.add(PunishResponseDTO.builder()
                        .punishId(punish.getPunishId())
                        .adminName(punish.getAdmin().getMemberName())
                        .memberName(punish.getTarget().getMemberName())
                        .reason(punish.getReport().getReportCategory().toString())
                        .judgeDate(judgeDate)
                        .releaseDate(releaseDate)
                        .build());
            }
            return PunishVO.builder()
                    .resultCode("success")
                    .resultDataNum(punishment.getTotalElements())
                    .resultData(resultData)
                    .currentPage(punishment.getNumber() + 1)
                    .totalPage(punishment.getTotalPages())
                    .sort(punishment.getSort())
                    .build();
        } catch (NullPointerException e) {
            throw new Exception("failed: 조회할 수 있는 데이터가 없습니다.");
        }
    }

    /**
     * 회원 아이디로 게시글 목록 조회
     * @param page
     * @param memberId
     * @return
     */
    @Override
    public PostVO findPostsById(int page, int memberId) throws Exception {
        try {
            Page<PostResponseDTO> postsById = postSearchService.findPostsById(page, memberId);
            List<PostResponseDTO> content = postsById.getContent();
            return PostVO.builder()
                    .resultCode("success")
                    .resultDataNum(postsById.getTotalElements())
                    .resultData(content)
                    .currentPage(postsById.getNumber() + 1)
                    .totalPage(postsById.getTotalPages())
                    .sort(postsById.getSort())
                    .build();
        } catch (NullPointerException e) {
            throw new Exception("failed: 조회할 수 있는 데이터가 없습니다.");
        }
    }

    /**
     * 전체 게시글 조회(관리자)
     * 블라인드된 글도 보임
     * @param period
     * @param page
     * @return
     */
    @Override
    public PostVO lookupAllPosts(PeriodRequestDTO period, int page) throws Exception {
        try {
            Page<PostResponseDTO> posts = postSearchService.lookupAllPosts(period.getStartDate(), period.getEndDate(), page);
            return PostVO.builder()
                    .resultCode("success")
                    .resultDataNum(posts.getTotalElements())
                    .resultData(posts.getContent())
                    .currentPage(posts.getNumber() + 1)
                    .totalPage(posts.getTotalPages())
                    .sort(posts.getSort())
                    .build();
        } catch (NullPointerException e) {
            throw new Exception("failed : 조회할 수 있는 데이터가 없습니다.");
        } catch (IllegalStateException e) {
            throw new Exception("failed : 날짜를 잘못 입력했습니다.");
        } catch (Exception e) {
            throw new Exception("failed : 뭔가 잘못됐습니다..");
        }
    }

    @Override
    public PeriodPostVO checkNewPosts(PeriodRequestDTO period, int page) throws Exception {
        try {
            Page<PeriodPostResponseDTO> newMember = postSearchService.checkNewposts(period.getStartDate(), period.getEndDate(), page);
            return PeriodPostVO.builder()
                    .resultCode("success")
                    .resultDataNum(newMember.getTotalElements())
                    .resultData(newMember.getContent())
                    .currentPage(newMember.getNumber() + 1)
                    .totalPage(newMember.getTotalPages())
                    .sort(newMember.getSort())
                    .build();
        } catch (NullPointerException e) {
            throw new Exception("failed : 조회할 수 있는 데이터가 없습니다.");
        } catch (IllegalStateException e) {
            throw new Exception("failed : 날짜를 잘못 입력했습니다.");
        } catch (Exception e) {
            throw new Exception("failed : 뭔가 잘못됐습니다..");
        }
    }
}