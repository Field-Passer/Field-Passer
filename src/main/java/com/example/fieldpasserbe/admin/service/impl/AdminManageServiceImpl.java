package com.example.fieldpasserbe.admin.service.impl;

import com.example.fieldpasserbe.admin.dto.BlindRequestDTO;
import com.example.fieldpasserbe.admin.entity.Admin;
import com.example.fieldpasserbe.admin.repository.AdminRepositoryJPA;
import com.example.fieldpasserbe.admin.service.AdminManageService;
import com.example.fieldpasserbe.admin.vo.SimpleVO;
import com.example.fieldpasserbe.member.service.MemberService;
import com.example.fieldpasserbe.post.entity.Post;
import com.example.fieldpasserbe.post.repository.PostRepositoryJPA;
import com.example.fieldpasserbe.post.service.PostSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminManageServiceImpl implements AdminManageService {

    private final MemberService memberService;
    private final PostRepositoryJPA postRepository;
    private final AdminRepositoryJPA adminRepository;

    /**
     * 일반 회원을 관리자로 등업
     * @param email
     * @return
     */
    @Override
    public SimpleVO promoteAdmin(String email) {
        try {
            Admin newAdmin = Admin.builder()
                    .member(memberService.findMemberByEmail(email).get())
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

    /*TODO
    *  - 주석 작성
    * */
    @Override
    @Transactional
    public SimpleVO blind(BlindRequestDTO blind) {
        Post post = postRepository.findByPostId(blind.getPostId()).get();
        if (blind.isBlind()) {
            post.BlindPost();
        }
        return SimpleVO.builder()
                .resultCode("success")
                .build();
    }
}
