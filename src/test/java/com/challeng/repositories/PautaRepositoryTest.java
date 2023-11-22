package com.challeng.repositories;

import com.challeng.domain.Pauta;
import com.challeng.repository.PautaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PautaRepositoryTest {


    @Autowired
    private PautaRepository repository;


    @Test
    public void testSavePauta() {
        var pauta = new Pauta();
        pauta.setDescricao("Teste pauta Repositorie");

        Pauta savedPauta = repository.save(pauta);

        Pauta pautaFounded = repository.findById(savedPauta.getId()).orElseThrow(EntityNotFoundException::new);

        assertNotNull(pautaFounded);
        assertEquals("Teste pauta Repositorie", pautaFounded.getDescricao());
    }

}
