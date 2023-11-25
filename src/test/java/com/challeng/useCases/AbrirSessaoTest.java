package com.challeng.useCases;

import com.challeng.domain.Pauta;
import com.challeng.domain.Sessao;
import com.challeng.dto.PautaDTO;
import com.challeng.repository.PautaRepository;
import com.challeng.repository.SessaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AbrirSessaoTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoRepository sessaoRepository;



    @InjectMocks
    private AbrirSessao abrirSessao;

    @Test
    public void shouldOpenSession() {

        var pautaId = 1L;
        int duracaoMinutos = 10;
        var start = LocalDateTime.now();
        var end = start.plusMinutes(duracaoMinutos);

        Pauta pautaMock = new Pauta();
        pautaMock.setId(pautaId);
        pautaMock.setDescricao("Pauta teste");

        when(pautaRepository.findById(pautaId)).thenReturn(Optional.of(pautaMock));

        when(sessaoRepository.save(any(Sessao.class))).thenAnswer(invocation -> {
            Sessao sessaoToSave = invocation.getArgument(0);
            sessaoToSave.setId(1L);
            return sessaoToSave;
        });

        var result = abrirSessao.execute(pautaId, duracaoMinutos);

        verify(sessaoRepository).save(any(Sessao.class));
        assertEquals(1L, result.id());
        assertEquals(pautaId, result.pauta());
    }
}
