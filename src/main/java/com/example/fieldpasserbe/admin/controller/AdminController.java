package com.example.fieldpasserbe.admin.controller;

import com.example.fieldpasserbe.admin.dto.PeriodRequestDTO;
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
    public PeriodMemberVO checknewMember(PeriodRequestDTO period) {
        return adminService.checkNewMember(period);
    }
}