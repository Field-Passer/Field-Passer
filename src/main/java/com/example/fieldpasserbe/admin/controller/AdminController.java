package com.example.fieldpasserbe.admin.controller;

import com.example.fieldpasserbe.admin.dto.PeriodRequestDTO;
import com.example.fieldpasserbe.admin.dto.PostResponseDTO;
import com.example.fieldpasserbe.admin.service.AdminLoginService;
import com.example.fieldpasserbe.admin.service.AdminManageService;
import com.example.fieldpasserbe.admin.service.AdminService;
import com.example.fieldpasserbe.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.admin.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminLoginService adminLoginService;
    private final AdminService adminService;
    private final AdminManageService adminManageService;

    @PostMapping("/admin/auth/login")
    public AdminLoginVO adminLogin(@RequestBody AdminLoginRequestDTO adminLoginRequest, HttpSession session) {
        try {
            return adminLoginService.adminLogin(adminLoginRequest, session);
        } catch (Exception e) {
            return AdminLoginVO.builder()
                    .resultCode(e.getMessage())
                    .build();
        }
    }

    @GetMapping("/admin/members")
    public MemberListVO lookUpMembers(@RequestParam(name = "page") int page) {
        try {
            return adminService.lookUpmembers(page);
        } catch (Exception e) {
            return MemberListVO.builder()
                    .resultCode(e.getMessage())
                    .build();
        }
    }

    @PutMapping("/admin/promote")
    public SimpleVO promoteAdmin(@RequestBody Map<String, String> map) {

        return adminManageService.promoteAdmin(map.get("email"));
    }

    @GetMapping("/admin/members/{memberId}")
    public MemberVO memberDetail(@PathVariable int memberId) {

        return adminService.memberDetail(memberId);
    }

    @GetMapping("/admin/membes/new")
    public PeriodMemberVO checkNewMember(PeriodRequestDTO period, int page) {
        return adminService.checkNewMember(period, page);
    }

    @GetMapping("/admin/members/punishment")
    public PunishVO lookupPunishment(int page) {
        return adminService.lookUpPunishment(page);
    }

    @GetMapping("/admin/board/members/{memberId}")
    public PostVO postByID(@PathVariable int memberId, @RequestParam(name = "page") int page) {
        System.out.println("memberId = " + memberId);
        System.out.println("page = " + page);
        return adminService.findPostsById(page, memberId);
    }

    @GetMapping("/admin/board")
    public PostVO lookupAllPosts(PeriodRequestDTO period, int page) {
        return adminService.lookupAllPosts(period, page);
    }

    @GetMapping("/admin/board/new")
    public PeriodPostVO checkNewPosts(PeriodRequestDTO period, int page) {
        return adminService.checkNewPosts(period, page);
    }
}