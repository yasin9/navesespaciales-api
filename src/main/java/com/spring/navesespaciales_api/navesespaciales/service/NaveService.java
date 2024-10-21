package com.spring.navesespaciales_api.navesespaciales.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.navesespaciales_api.navesespaciales.dto.NaveDTO;

public interface NaveService {
    Page<NaveDTO> getAllNaves(Pageable pageable);

    NaveDTO getNaveById(Long id);

    Page<NaveDTO> searchNaves(String name, Pageable pageable);

    NaveDTO createNave(NaveDTO NavesDTO);

    NaveDTO updateNave(Long id, NaveDTO NavesDTO);

    void deleteNave(Long id);
}
