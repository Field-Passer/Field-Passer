package com.example.fieldpasserbe.admin.service;

import com.example.fieldpasserbe.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.admin.vo.AdminLoginVO;
import com.example.fieldpasserbe.admin.vo.MemberListVO;
import com.example.fieldpasserbe.admin.vo.MemberVO;
import com.example.fieldpasserbe.admin.vo.SimpleVO;

import javax.servlet.http.HttpSession;

public interface AdminService {
    AdminLoginVO adminLogin(AdminLoginRequestDTO admin, HttpSession session) throws Exception;
    MemberListVO lookUpmembers(int page) throws Exception;
    SimpleVO promoteAdmin(String email);

    MemberVO memberDetail(int memberId);
}
