package com.example.fieldpasserbe.admin.service;

import com.example.fieldpasserbe.admin.dto.PeriodRequestDTO;
import com.example.fieldpasserbe.admin.vo.*;

public interface AdminService {

    MemberListVO lookUpmembers(int page) throws Exception;
    MemberVO memberDetail(int memberId) throws Exception;
    PeriodMemberVO checkNewMember(PeriodRequestDTO period, int page) throws Exception;
    PunishVO lookUpPunishment(int page) throws Exception;
    PostVO findPostsById(int page, int memberId) throws Exception;
    PostVO lookupAllPosts(PeriodRequestDTO period, int page) throws Exception;
    PeriodPostVO checkNewPosts(PeriodRequestDTO period, int page) throws Exception;
}
