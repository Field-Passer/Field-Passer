package com.example.fieldpasserbe.admin.controller;

import com.example.fieldpasserbe.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.admin.dto.AdminLoginResponceDTO;
import com.example.fieldpasserbe.admin.service.AdminService;
import com.example.fieldpasserbe.admin.service.impl.AdminServiceImpl;
import com.example.fieldpasserbe.admin.vo.AdminLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
}
