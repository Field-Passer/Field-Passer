package com.example.fieldpasserbe.admin.repository;

import com.example.fieldpasserbe.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepositoryJPA extends JpaRepository<Admin, Integer> {
}
