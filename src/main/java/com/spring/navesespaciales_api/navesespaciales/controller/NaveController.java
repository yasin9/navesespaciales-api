package com.spring.navesespaciales_api.navesespaciales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.spring.navesespaciales_api.navesespaciales.dto.NaveDTO;
import com.spring.navesespaciales_api.navesespaciales.service.NaveService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/naves")
public class NaveController {

    @Autowired
    private NaveService naveService;

    @GetMapping
    public ResponseEntity<Page<NaveDTO>> getAllNaves(Pageable pageable) {
        return ResponseEntity.ok(naveService.getAllNaves(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NaveDTO> getNaveById(@PathVariable Long id) {
        return ResponseEntity.ok(naveService.getNaveById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<NaveDTO>> searchNaves(@RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(naveService.searchNaves(name, pageable));
    }

    @PostMapping
    public ResponseEntity<NaveDTO> createNave(@Valid @RequestBody NaveDTO NaveDTO) {
        return ResponseEntity.ok(naveService.createNave(NaveDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NaveDTO> updateNave(@PathVariable Long id, @Valid @RequestBody NaveDTO NaveDTO) {
        return ResponseEntity.ok(naveService.updateNave(id, NaveDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNave(@PathVariable Long id) {
        naveService.deleteNave(id);
        return ResponseEntity.noContent().build();
    }
}
