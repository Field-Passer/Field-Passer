package com.example.fieldpasserbe.support.service.impl;

import com.example.fieldpasserbe.support.repository.PunishRepositoryJPA;
import com.example.fieldpasserbe.support.service.PunishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PunishServiceImpl implements PunishService {

    @Autowired
    PunishRepositoryJPA punishRepository;

    @Override
    public Long countBytargetId(int id) {
        return punishRepository.countBytargetId(id);
    }
}
