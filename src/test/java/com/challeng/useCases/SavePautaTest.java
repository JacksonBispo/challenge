package com.challeng.useCases;

import com.challeng.domain.Pauta;
import com.challeng.dto.PautaDTO;
import com.challeng.repository.PautaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class SavePautaTest {

    @InjectMocks
    private SavePauta savePauta;

    @Mock
    private PautaRepository pautaRespository;

    @Test
    @DisplayName("Should save a Pauta with sucess")
    void shouldSavePautaSuccess(){

        var pauta = new PautaDTO(
                null,
                "Pauta test"

        );

        var entityPauta = new Pauta(pauta.id(), pauta.descricao(), null);
        Mockito.when(pautaRespository.save(any(Pauta.class))).thenReturn(entityPauta);

        var result = savePauta.execute(pauta);

        Mockito.verify(pautaRespository).save(any(Pauta.class));

        Assertions.assertEquals(result, entityPauta);
    }
}
