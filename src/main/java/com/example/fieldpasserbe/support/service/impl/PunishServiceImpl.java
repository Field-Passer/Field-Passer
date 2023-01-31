package com.example.fieldpasserbe.support.service.impl;

import com.example.fieldpasserbe.support.repository.PunishRepositoryJPA;
import com.example.fieldpasserbe.support.service.PunishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PunishServiceImpl implements PunishService {

    private final PunishRepositoryJPA punishRepository;

    @Override
    public Long countBytargetId(int id) {
        return punishRepository.countBytargetId(id);
    }
}
