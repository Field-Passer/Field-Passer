package com.example.fieldpasserbe.support.service.impl;

import com.example.fieldpasserbe.support.dto.PunishDTO;
import com.example.fieldpasserbe.support.dto.PunishResponceDTO;
import com.example.fieldpasserbe.support.entity.Punish;
import com.example.fieldpasserbe.support.repository.PunishRepositoryJPA;
import com.example.fieldpasserbe.support.service.PunishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PunishServiceImpl implements PunishService {

    private final int contentsSize = 10;
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

    /**
     * 징계 중인 회원 조회
     * @param page
     * @return
     */
    @Override
    public Page<Punish> findPunishment(int page) throws NullPointerException{
        PageRequest pageRequest = PageRequest.of(page - 1, contentsSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Punish> totalPunish = punishRepository.findTotalPunish(pageRequest);
        if (totalPunish.getContent().isEmpty()) {
            throw new NullPointerException("조회할 수 있는 글이 없습니다.");
        } else {
            return totalPunish;
        }
    }
}
