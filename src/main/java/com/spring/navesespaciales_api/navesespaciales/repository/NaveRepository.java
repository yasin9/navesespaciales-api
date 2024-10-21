package com.spring.navesespaciales_api.navesespaciales.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.navesespaciales_api.navesespaciales.model.Nave;

public interface NaveRepository extends JpaRepository<Nave, Long> {
    Page<Nave> findByNameContaining(String name, Pageable pageable);
}