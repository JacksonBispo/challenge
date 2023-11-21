package com.challeng.useCases;

import com.challeng.domain.Pauta;
import com.challeng.repository.PautaRespository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SavePautaTest {

    @InjectMocks
    private SavePauta savePauta;

    @Mock
    private PautaRespository pautaRespository;
    @Test
    @DisplayName("Should save a Pauta with sucess")
    void shouldSavePautaSuccess(){
        var pauta = new Pauta(
                null,
                "Pauta Test"
        );
        Mockito.when(pautaRespository.save(pauta)).thenReturn(pauta);
    }
}
