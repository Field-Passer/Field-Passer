package com.example.fieldpasserbe.admin.service;

import com.example.fieldpasserbe.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.admin.vo.AdminLoginVO;
import com.example.fieldpasserbe.admin.vo.MemberListVO;
import com.example.fieldpasserbe.admin.vo.MemberVO;
import com.example.fieldpasserbe.admin.vo.SimpleVO;

import javax.servlet.http.HttpSession;

public interface AdminService {

    MemberListVO lookUpmembers(int page) throws Exception;
    MemberVO memberDetail(int memberId);
}
