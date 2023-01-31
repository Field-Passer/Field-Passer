package com.example.fieldpasserbe.admin.service.impl;

import com.example.fieldpasserbe.admin.service.AdminService;
import com.example.fieldpasserbe.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.admin.dto.AdminLoginResponceDTO;
import com.example.fieldpasserbe.admin.dto.MemberListDTO;
import com.example.fieldpasserbe.admin.entity.Admin;
import com.example.fieldpasserbe.admin.repository.AdminRepositoryJPA;
import com.example.fieldpasserbe.admin.vo.AdminLoginVO;
import com.example.fieldpasserbe.admin.vo.MemberListVO;
import com.example.fieldpasserbe.admin.vo.SimpleVO;
import com.example.fieldpasserbe.board.service.BoardService;
import com.example.fieldpasserbe.member.entity.Member;
import com.example.fieldpasserbe.member.service.MemberService;
import com.example.fieldpasserbe.support.service.PunishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final MemberService memberService;
    private final BoardService boardService;
    private final PunishService punishService;
    private final AdminRepositoryJPA adminRepository;

    /**
     * 로그인
     * @param admin
     * @param session
     * @return
     * @throws Exception
     */
    @Override
    public AdminLoginVO adminLogin(AdminLoginRequestDTO admin, HttpSession session) throws Exception{

        if (!isValidEmail(admin.getEmail())) {
            throw new Exception("failed : 입력이 이메일 형식이 아닙니다.");
        }

        try {
            Member member = memberService.findAdminByEmail(admin.getEmail()).get();
            if (isValidPassword(admin, member)) {
                session.setAttribute("email", member.getEmail());
                memberService.updateVisitCount(member.getId());
                return AdminLoginVO.builder()
                        .resultCode("success")
                        .resultData(AdminLoginResponceDTO.builder()
                                .email(member.getEmail())
                                .profileImg(member.getProfileImg())
                                .memberName(member.getMemberName())
                                .build())
                        .build();
            } else {
                throw new Exception("failed : 비밀번호가 틀렸습니다.");
            }
        } catch (NullPointerException e) {
            throw new Exception("failed : 존재하지 않는 회원입니다.");
        }
    }

    /**
     * 비밀번호 매칭 검사
     * @param admin
     * @param member
     * @return
     */
    private boolean isValidPassword(AdminLoginRequestDTO admin, Member member) {
        if (member.getPassword().equals(admin.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 이메일 형식 유효성 검사
     * @param email
     * @return
     */
    private static boolean isValidEmail(String email) {
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 전체 회원 조회
     * @return
     * @throws Exception
     */
    @Override
    public MemberListVO lookUpmembers(int page) throws Exception{
        try {
            Page<Member> members = memberService.findAllMembers(page);
            Sort sort = members.getSort();
            List<MemberListDTO> resultData = new ArrayList<>();
            for (Member member : members.getContent()) {
                resultData.add(MemberListDTO.builder()
                                .email(member.getEmail())
                                .memberName(member.getMemberName())
                                .signupDate(member.getSignUpDate())
                                .postCount(boardService.countPostById(member.getId()))  //글 개수
                                .visitCount(member.getVisitCount())
                                .privilege(member.convertPrivilege(member.getPrivilege()))
                                .reportNum(punishService.countBytargetId(member.getId()))  //신고 수
                                .authority(member.convertAuthority(member.getAuthority()))
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

    @Override
    public SimpleVO promoteAdmin(String email) {
        try {
            Member member = memberService.findMemberByEmail(email).get();
            Admin newAdmin = Admin.builder()
                    .member(member)
                    .promoteDate(LocalDateTime.now())
                    .build();
            newAdmin.promote();
            adminRepository.save(newAdmin);
            return SimpleVO.builder()
                    .resultCode("success")
                    .build();
        } catch (NullPointerException e) {
            return SimpleVO.builder()
                    .resultCode("failed : 승격에 실패했습니다.")
                    .build();
        }
    }
}
