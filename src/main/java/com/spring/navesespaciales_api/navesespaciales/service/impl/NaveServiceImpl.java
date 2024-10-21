package com.spring.navesespaciales_api.navesespaciales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.navesespaciales_api.navesespaciales.dto.NaveDTO;
import com.spring.navesespaciales_api.navesespaciales.model.Nave;
import com.spring.navesespaciales_api.navesespaciales.repository.NaveRepository;
import com.spring.navesespaciales_api.navesespaciales.service.NaveService;

import com.spring.navesespaciales_api.navesespaciales.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class  NaveServiceImpl implements NaveService {

    @Autowired
    private NaveRepository naveRepository;

    @Cacheable("naves")
    @Override
    public Page<NaveDTO> getAllNaves(Pageable pageable) {
        return naveRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Cacheable("naves")
    @Override
    public NaveDTO getNaveById(Long id) {
        Nave nave = naveRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Nave not found"));
        return convertToDTO(nave);
    }

    @Cacheable("naves")
    @Override
    public Page<NaveDTO> searchNaves(String name, Pageable pageable) {
        return naveRepository.findByNameContaining(name, pageable).map(this::convertToDTO);
    }

    @Cacheable("naves")
    @Override
    public NaveDTO createNave(NaveDTO navesDTO) {
        Nave nave = convertToEntity(navesDTO);
        return convertToDTO(naveRepository.save(nave));
    }

    @Cacheable("naves")
    @Override
    public NaveDTO updateNave(Long id, NaveDTO navesDTO) {
        Nave nave = naveRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nave no encontrada"));
        nave.setName(navesDTO.getName());
        nave.setSeries(navesDTO.getSeries());
        nave.setTipo(navesDTO.getTipo());
        return convertToDTO(naveRepository.save(nave));
    }

    @Cacheable("naves")
    @Override
    public void deleteNave(Long id) {
        Nave nave = naveRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Nave no encontrada"));
            naveRepository.delete(nave);
    }
    
    private NaveDTO convertToDTO(Nave nave) {
        NaveDTO dto = new NaveDTO();
        dto.setId(nave.getId());
        dto.setName(nave.getName());
        dto.setSeries(nave.getSeries());
        dto.setTipo(nave.getTipo());
        return dto;
    }

    private Nave convertToEntity(NaveDTO dto) {
        Nave nave = new Nave();
        nave.setName(dto.getName());
        nave.setSeries(dto.getSeries());
        nave.setTipo(dto.getTipo());
        return nave;
    }

}
