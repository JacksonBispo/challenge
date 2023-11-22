package com.challeng.useCases;

import com.challeng.domain.Associado;
import com.challeng.domain.Sessao;
import com.challeng.domain.Voto;
import com.challeng.domain.VotoEnum;
import com.challeng.repository.SessaoRepository;
import com.challeng.repository.VotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class RegistraVotoTest {



    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private RegistrarVotos registrarVotos;

    @Test
    public void testRegistrarVoto() {
        Long sessaoVotacaoId = 1L;
        Long associadoId = 2L;

        var sessaoVotacaoMock = new Sessao();
        sessaoVotacaoMock.setId(sessaoVotacaoId);
        sessaoVotacaoMock.setInicio(LocalDateTime.now());
        sessaoVotacaoMock.setFim(LocalDateTime.now().plusMinutes(10));

        when(sessaoRepository.findById(sessaoVotacaoId)).thenReturn(Optional.of(sessaoVotacaoMock));

        when(votoRepository.existsBySessaoVotacaoIdAndAssociadoId(sessaoVotacaoId, associadoId)).thenReturn(false);

        var associado = new Associado();
        associado.setId(1L);
        associado.setName("testes");

        Voto voto = new Voto();
        voto.setSessaoVotacao(sessaoVotacaoMock);
        voto.setAssociado(associado);

        registrarVotos.execute(voto);

        // Verificar se o método save do repositório Voto foi chamado
        verify(votoRepository, times(1)).save(voto);
    }

    @Test
    public void testRegistrarVotoJavotou() {
        Long sessaoVotacaoId = 1L;
        Long associadoId = 2L;
        var associado = new Associado();
        associado.setId(associadoId);
        associado.setName("testes");

        var sessaoVotacaoMock = new Sessao();
        sessaoVotacaoMock.setId(sessaoVotacaoId);
        sessaoVotacaoMock.setInicio(LocalDateTime.now());
        sessaoVotacaoMock.setFim(LocalDateTime.now().plusMinutes(10));

        var voto = new Voto();
        voto.setVoto(VotoEnum.NAO);
        voto.setSessaoVotacao(sessaoVotacaoMock);
        voto.setAssociado(associado);
        when(sessaoRepository.findById(sessaoVotacaoId)).thenReturn(Optional.of(sessaoVotacaoMock));

        when(votoRepository.existsBySessaoVotacaoIdAndAssociadoId(sessaoVotacaoId, associadoId)).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> {
            registrarVotos.execute(voto);
        });
    }
}
