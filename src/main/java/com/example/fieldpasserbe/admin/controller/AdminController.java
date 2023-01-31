package com.example.fieldpasserbe.admin.controller;

import com.example.fieldpasserbe.admin.service.AdminService;
import com.example.fieldpasserbe.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.admin.vo.AdminLoginVO;
import com.example.fieldpasserbe.admin.vo.MemberListVO;
import com.example.fieldpasserbe.admin.vo.SimpleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/admin/auth/login")
    public AdminLoginVO adminLogin(@RequestBody AdminLoginRequestDTO adminLoginRequest, HttpSession session) {
        try {
            return adminService.adminLogin(adminLoginRequest, session);
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
        return adminService.promoteAdmin(map.get("email"));
    }
}