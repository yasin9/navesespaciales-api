package com.spring.navesespaciales_api.navesespaciales.repository;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.navesespaciales_api.navesespaciales.model.Nave;

import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class NaveRepositoryTest {
    
    @Autowired
    private NaveRepository naveRepository;

    @BeforeEach
    void setUp() {
        naveRepository.deleteAll();

    }

     @Test
    public void testSaveNave() {
        Nave nave = new Nave("Destructor Estelar", "Star Wars", "Buque de Guerra");
        Nave saveNave = naveRepository.save(nave);

        assertThat(saveNave.getId()).isNotNull();
        assertThat(saveNave.getName()).isEqualTo("Buque de Guerra");
    }
    @Test
    public void testFindById() {
        Nave nave = naveRepository.save(new Nave("Halcón Milenario", "Star Wars", "Nave de carga"));
        Optional<Nave> foundNave = naveRepository.findById(nave.getId());

        assertThat(foundNave).isPresent();
        assertThat(foundNave.get().getName()).isEqualTo("Halcón Milenario");
    }

    @Test
    public void testFindAll() {
        naveRepository.save(new Nave("X-Wing", "Star Wars", "Nave de carga"));
        naveRepository.save(new Nave("TIE Fighter", "Star Wars", "Nave de carga"));

        List<Nave> nave = naveRepository.findAll();

        assertThat(nave.size()).isEqualTo(2);
    }

    @Test
    public void testDeleteNave() {
        Nave nave = naveRepository.save(new Nave("TIE Avanzado", "Star Wars", "Nave de carga"));

        naveRepository.deleteById(nave.getId());
        Optional<Nave> deletedNave = naveRepository.findById(nave.getId());

        assertThat(deletedNave).isEmpty();
    }

}
