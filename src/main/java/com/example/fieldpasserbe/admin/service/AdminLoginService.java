package com.example.fieldpasserbe.admin.service;

import com.example.fieldpasserbe.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.admin.vo.AdminLoginVO;

import javax.servlet.http.HttpSession;

public interface AdminLoginService {
    AdminLoginVO adminLogin(AdminLoginRequestDTO admin, HttpSession session) throws Exception;
}
