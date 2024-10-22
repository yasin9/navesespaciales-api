package com.spring.navesespaciales_api.navesespaciales.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.navesespaciales_api.navesespaciales.dto.NaveDTO;
import com.spring.navesespaciales_api.navesespaciales.model.Nave;
import com.spring.navesespaciales_api.navesespaciales.repository.NaveRepository;
import com.spring.navesespaciales_api.navesespaciales.service.NaveService;

import com.spring.navesespaciales_api.navesespaciales.exception.ResourceNotFoundException;
import com.spring.navesespaciales_api.navesespaciales.mapper.NaveMapper;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NaveServiceImpl implements NaveService {

    @Autowired
    private NaveRepository naveRepository;

    @Cacheable("naves")
    @Override
    public Page<NaveDTO> getAllNaves(Pageable pageable) {
        return naveRepository.findAll(pageable)
                .map(NaveMapper::toDTO);
    }

    @Cacheable("naves")
    @Override
    public NaveDTO getNaveById(Long id) {
        Nave nave = naveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nave not found with id: " + id));
        return NaveMapper.toDTO(nave);

    }

    @Cacheable("naves")
    @Override
    public Page<NaveDTO> searchNaves(String name, Pageable pageable) {
        List<NaveDTO> filteredNaves = naveRepository.findAll().stream()
                .filter(nave -> nave.getName().toLowerCase().contains(name.toLowerCase()))
                .map(NaveMapper::toDTO)
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filteredNaves.size());

        List<NaveDTO> paginatedNaves = filteredNaves.subList(start, end);

        return new PageImpl<>(paginatedNaves, pageable, filteredNaves.size());
    }

    @Cacheable("naves")
    @Override
    public NaveDTO createNave(NaveDTO navesDTO) {
        Nave nave = NaveMapper.toEntity(navesDTO);
        Nave savedNave = naveRepository.save(nave);
        return NaveMapper.toDTO(savedNave);
    }

    @Cacheable("naves")
    @Override
    public NaveDTO updateNave(Long id, NaveDTO navesDTO) {

        Nave nave = naveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nave not found with id: " + id));

        nave.setName(navesDTO.getName());
        nave.setSeries(navesDTO.getSeries());
        nave.setTipo(navesDTO.getTipo());

        Nave updatedNave = naveRepository.save(nave);
        return NaveMapper.toDTO(updatedNave);
    }

    @Cacheable("naves")
    @Override
    public void deleteNave(Long id) {
        Nave nave = naveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nave not found with id: " + id));
        naveRepository.delete(nave);
    }

}
