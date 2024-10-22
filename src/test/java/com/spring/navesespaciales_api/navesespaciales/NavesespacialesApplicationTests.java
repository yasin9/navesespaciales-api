package com.spring.navesespaciales_api.navesespaciales;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import com.spring.navesespaciales_api.navesespaciales.dto.NaveDTO;
import com.spring.navesespaciales_api.navesespaciales.exception.ResourceNotFoundException;
import com.spring.navesespaciales_api.navesespaciales.model.Nave;
import com.spring.navesespaciales_api.navesespaciales.repository.NaveRepository;
import com.spring.navesespaciales_api.navesespaciales.service.NaveService;

class NavesespacialesApplicationTests {

    @Mock
    private NaveRepository naveRepository;

    @InjectMocks
    private NaveService naveService;

    private Nave nave;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        nave = new Nave();
        nave.setId(1L);
        nave.setName("X-Wing");
        nave.setSeries("Star Wars");
    }

    @Test
    void testGetNaveByIdSuccess() throws ResourceNotFoundException {
        when(naveRepository.findById(1L)).thenReturn(Optional.of(nave));

        NaveDTO foundNave = naveService.getNaveById(1L);

        assertNotNull(foundNave);
        assertEquals("X-Wing", foundNave.getName());
        assertEquals("Star Wars", foundNave.getSeries());

        verify(naveRepository, times(1)).findById(1L);
    }

    @Test
    void testGetNaveByIdThrowsException() {
        when(naveRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            naveService.getNaveById(1L);
        });

        assertEquals("Nave no encontrado para este id :: 1", exception.getMessage());
        verify(naveRepository, times(1)).findById(1L);
    }
}
