package com.spring.navesespaciales_api.navesespaciales.mapper;

import com.spring.navesespaciales_api.navesespaciales.dto.NaveDTO;
import com.spring.navesespaciales_api.navesespaciales.model.Nave;

public class NaveMapper {
    
    // Convertir de Entidad a DTO
    public static NaveDTO toDTO(Nave nave) {
        return new NaveDTO(
            nave.getId(),
            nave.getName(),
            nave.getSeries(),
            nave.getTipo()
        );
    }

    // Convertir de DTO a Entidad
    public static Nave toEntity(NaveDTO dto) {
        Nave nave = new Nave();
        nave.setName(dto.getName());
        nave.setSeries(dto.getSeries());
        nave.setTipo(dto.getTipo());
        return nave;
    }
}
