package com.example.fieldpasserbe.lee.admin.service;

import com.example.fieldpasserbe.lee.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.lee.admin.vo.AdminLoginVO;
import com.example.fieldpasserbe.lee.admin.vo.MemberListVO;
import com.example.fieldpasserbe.lee.admin.vo.SimpleVO;

import javax.servlet.http.HttpSession;

public interface AdminService {
    AdminLoginVO adminLogin(AdminLoginRequestDTO admin, HttpSession session) throws Exception;
    MemberListVO lookUpmembers() throws Exception;

    SimpleVO promoteAdmin(String email);
}
