package com.spring.navesespaciales_api.navesespaciales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.spring.navesespaciales_api.navesespaciales.dto.NaveDTO;
import com.spring.navesespaciales_api.navesespaciales.service.NaveService;

import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/naves")
public class NaveController {

    @Autowired
    private NaveService naveService;

    @Operation(summary = "Obtener todas las naves espaciales")
    @GetMapping
    public ResponseEntity<Page<NaveDTO>> getAllNaves(Pageable pageable) {
        Page<NaveDTO> naves = naveService.getAllNaves(pageable);
        return new ResponseEntity<>(naves, HttpStatus.OK);

    }

    @Operation(summary = "Obtener una nave espacial por ID")
    @GetMapping("/{id}")
    public ResponseEntity<NaveDTO> getNaveById(@PathVariable Long id) {
        NaveDTO nave = naveService.getNaveById(id);
        return new ResponseEntity<>(nave, HttpStatus.OK);
    }

    @Operation(summary = "Buscar naves especiales")
    @GetMapping("/search")
    public ResponseEntity<Page<NaveDTO>> searchNaves(@RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(naveService.searchNaves(name, pageable));
    }

    @Operation(summary = "Crear nave espacial")
    @PostMapping
    public ResponseEntity<NaveDTO> createNave(@Valid @RequestBody NaveDTO NaveDTO) {
        return new ResponseEntity<>(naveService.createNave(NaveDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar nave espacial")
    @PutMapping("/{id}")
    public ResponseEntity<NaveDTO> updateNave(@PathVariable Long id, @Valid @RequestBody NaveDTO naveDTO) {
        return new ResponseEntity<>(naveService.updateNave(id, naveDTO), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar nave espacial")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNave(@PathVariable Long id) {
        naveService.deleteNave(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
