package com.example.fieldpasserbe.admin.service;

import com.example.fieldpasserbe.admin.dto.PeriodRequestDTO;
import com.example.fieldpasserbe.admin.vo.*;

public interface AdminService {

    MemberListVO lookUpmembers(int page) throws Exception;
    MemberVO memberDetail(int memberId);

    PeriodMemberVO checkNewMember(PeriodRequestDTO period);
}
