package com.example.fieldpasserbe.admin.service;

import com.example.fieldpasserbe.admin.dto.BlindRequestDTO;
import com.example.fieldpasserbe.admin.vo.SimpleVO;

public interface AdminManageService {
    SimpleVO promoteAdmin(String email);

    SimpleVO blind(BlindRequestDTO blind);
}
