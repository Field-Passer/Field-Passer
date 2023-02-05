package com.example.fieldpasserbe.support.service;

import com.example.fieldpasserbe.support.dto.PunishResponceDTO;
import com.example.fieldpasserbe.support.dto.PunishDTO;
import com.example.fieldpasserbe.support.entity.Punish;
import org.springframework.data.domain.Page;

public interface PunishService {
    Long countBytargetId(int id);

    PunishDTO checkPunish(int id);

    Page<Punish> findPunishment(int page) throws NullPointerException;
}
