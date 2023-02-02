package com.example.fieldpasserbe.support.service;

import com.example.fieldpasserbe.support.dto.PunishDTO;

public interface PunishService {
    Long countBytargetId(int id);

    PunishDTO checkPunish(int id);
}
