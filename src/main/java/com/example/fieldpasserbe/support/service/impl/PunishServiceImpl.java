package com.example.fieldpasserbe.support.service.impl;

import com.example.fieldpasserbe.support.dto.PunishDTO;
import com.example.fieldpasserbe.support.entity.Punish;
import com.example.fieldpasserbe.support.repository.PunishRepositoryJPA;
import com.example.fieldpasserbe.support.service.PunishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PunishServiceImpl implements PunishService {

    private final PunishRepositoryJPA punishRepository;

    /**
     * id로 징계 횟수 확인
     * @param id
     * @return
     */
    @Override
    public Long countBytargetId(int id) {
        return punishRepository.countBytargetId(id);
    }

    /**
     * 현재 징계 중인지 확인
     * @param id
     * @return
     */
    @Override
    public PunishDTO checkPunish(int id) {
        Optional<Punish> punish = punishRepository.findPunishBytargetMemberId(id);
        if (punish.isPresent()) {
            return PunishDTO.builder()
                    .punishResult(true)
                    .punishId(punish.get().getPunishId())
                    .judgeDate(punish.get().getPunishPeriod().getJudgeDate())
                    .releaseDate(punish.get().getPunishPeriod().getReleaseDate())
                    .build();
        } else {
            return PunishDTO.builder()
                    .punishResult(false)
                    .build();
        }
    }
}
