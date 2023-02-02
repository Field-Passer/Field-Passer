package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.post.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StadiumRepositoryJPA extends JpaRepository<Stadium, Integer> {

    List<Stadium> findByCategory_CategoryNameAndDistrict_DistrictName(String category, String district);
    Optional<Stadium> findByStadiumName(String stadiumName);
}
