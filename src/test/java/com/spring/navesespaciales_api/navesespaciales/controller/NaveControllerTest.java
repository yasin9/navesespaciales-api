package com.spring.navesespaciales_api.navesespaciales.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.navesespaciales_api.navesespaciales.model.Nave;
import com.spring.navesespaciales_api.navesespaciales.repository.NaveRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
@AutoConfigureMockMvc
class NavesespecialesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NaveRepository naveRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        naveRepository.deleteAll();
    }

    @Test
    void testCreateNave() throws Exception {

        Nave nave = new Nave("X-Wing", "Star Wars", "Nave de carga");
        MvcResult result = mockMvc.perform(post("/api/v1/naves")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nave)))
                .andExpect(status().isCreated()) // Esperamos un 201 Created
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        Nave createdNave = objectMapper.readValue(responseContent, Nave.class);

        assertThat(createdNave.getId()).isNotNull();
        assertThat(createdNave.getName()).isEqualTo("X-Wing");
    }

    @Test
    void testGetNaveById() throws Exception {
        Nave nave = naveRepository.save(new Nave("Halcón Milenario", "Star Wars", "Nave de carga"));

        mockMvc.perform(get("/api/v1/naves/{id}", nave.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Halcón Milenario"))
                .andExpect(jsonPath("$.series").value("Star Wars"));
    }

    @Test
    void testGetAllNaves() throws Exception {
        naveRepository.save(new Nave("Halcón Milenario", "Star Wars", "Nave de carga"));
        naveRepository.save(new Nave("X-Wing", "Star Wars", "Nave de carga"));

        mockMvc.perform(get("/api/v1/naves?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2));
    }

    @Test
    void testUpdateNave() throws JsonProcessingException, Exception {
        Nave nave = naveRepository.save(new Nave("Halcón Milenario", "Star Wars", "Nave de carga"));
       
        nave.setName("TIE Avanzado");
        mockMvc.perform(put("/api/v1/naves/{id}", nave.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TIE Avanzado")); 
    }

    @Test
    void testDeleteNave() throws Exception {
        Nave nave = naveRepository.save(new Nave("Esclavo I", "Star Wars", "Nave de carga"));

        mockMvc.perform(delete("/api/v1/naves/{id}", nave.getId()))
                .andExpect(status().isNoContent());

        Optional<Nave> deletedNave = naveRepository.findById(nave.getId());
        assertThat(deletedNave).isEmpty();
    }

}
