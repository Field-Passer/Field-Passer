package com.example.fieldpasserbe.post.repository;

import com.example.fieldpasserbe.post.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepositoryJPA extends JpaRepository<District, Integer> {

    Optional<District> findByDistrictName(String districtName);
}
