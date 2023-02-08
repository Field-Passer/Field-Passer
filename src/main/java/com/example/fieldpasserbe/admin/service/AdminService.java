package com.example.fieldpasserbe.admin.service;

import com.example.fieldpasserbe.admin.dto.PeriodRequestDTO;
import com.example.fieldpasserbe.admin.vo.*;

public interface AdminService {

    MemberListVO lookUpmembers(int page) throws Exception;
    MemberVO memberDetail(int memberId);
    PeriodMemberVO checkNewMember(PeriodRequestDTO period, int page);
    PunishVO lookUpPunishment(int page);
    PostVO findPostsById(int page, int memberId);
    PostVO lookupAllPosts(PeriodRequestDTO period, int page);
    PeriodPostVO checkNewPosts(PeriodRequestDTO period, int page);
}
