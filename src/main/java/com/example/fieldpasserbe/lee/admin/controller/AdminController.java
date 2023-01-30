package com.example.fieldpasserbe.lee.admin.controller;

import com.example.fieldpasserbe.lee.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.lee.admin.service.AdminService;
import com.example.fieldpasserbe.lee.admin.vo.AdminLoginVO;
import com.example.fieldpasserbe.lee.admin.vo.MemberListVO;
import com.example.fieldpasserbe.lee.admin.vo.SimpleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/auth/login")
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
    public SimpleVO promoteAdmin(@RequestBody Map<String, String> map) {
        return adminService.promoteAdmin(map.get("email"));
    }
}
