package com.challeng.useCases;

import com.challeng.configuration.swagger.FeatureToogleConfig;
import com.challeng.domain.Associado;
import com.challeng.domain.Sessao;
import com.challeng.domain.Voto;
import com.challeng.domain.VotoEnum;
import com.challeng.dto.StatusCpfDTO;
import com.challeng.dto.VotoDTO;
import com.challeng.repository.AssociadoRepository;
import com.challeng.repository.SessaoRepository;
import com.challeng.repository.VotoRepository;
import com.challeng.service.CheckingCPF;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Mock
    private AssociadoRepository associadoRepository;

    @Mock
    private CheckingCPF checkingCPF;

    @Mock
    private FeatureToogleConfig featureToogleConfig;

    @InjectMocks
    private RegistrarVotos registrarVotos;

    @Test
    public void testRegistrarVotoWithFeatureToggleEnable() {
        Long sessaoVotacaoId = 1L;
        Long associadoId = 2L;

        var sessaoVotacaoMock = new Sessao();
        sessaoVotacaoMock.setId(sessaoVotacaoId);
        sessaoVotacaoMock.setInicio(LocalDateTime.now());
        sessaoVotacaoMock.setFim(LocalDateTime.now().plusMinutes(10));
        when(sessaoRepository.findById(sessaoVotacaoId)).thenReturn(Optional.of(sessaoVotacaoMock));

        when(votoRepository.existsBySessaoVotacaoIdAndAssociadoId(sessaoVotacaoId, associadoId)).thenReturn(false);

        when(votoRepository.save(any(Voto.class))).thenAnswer(invocation -> invocation.getArgument(0));


        var associado = new Associado();
        associado.setId(associadoId);
        associado.setName("testes");
        associado.setCpf("39219796848");

        when(associadoRepository.findById(associadoId)).thenReturn(Optional.of(associado));

        when(checkingCPF.checkCpf("39219796848")).thenReturn(new StatusCpfDTO("ABLE_TO_VOTE"));

        when(featureToogleConfig.getCheckingCpf()).thenReturn(true);
        var voto = new VotoDTO(
                sessaoVotacaoId,
                associadoId,
                VotoEnum.NAO
        );

        var votoEntity = new Voto();
        votoEntity.setAssociado(associado);
        votoEntity.setSessaoVotacao(sessaoVotacaoMock);
        sessaoVotacaoMock.setVoto(votoEntity);

        registrarVotos.execute(voto);

    }

    @Test
    public void testRegistrarVotoWithFeatureToggleDisable() {
        Long sessaoVotacaoId = 1L;
        Long associadoId = 2L;

        var sessaoVotacaoMock = new Sessao();
        sessaoVotacaoMock.setId(sessaoVotacaoId);
        sessaoVotacaoMock.setInicio(LocalDateTime.now());
        sessaoVotacaoMock.setFim(LocalDateTime.now().plusMinutes(10));
        when(sessaoRepository.findById(sessaoVotacaoId)).thenReturn(Optional.of(sessaoVotacaoMock));

        when(votoRepository.existsBySessaoVotacaoIdAndAssociadoId(sessaoVotacaoId, associadoId)).thenReturn(false);

        when(votoRepository.save(any(Voto.class))).thenAnswer(invocation -> invocation.getArgument(0));


        var associado = new Associado();
        associado.setId(associadoId);
        associado.setName("testes");
        associado.setCpf("39219796848");

        when(associadoRepository.findById(associadoId)).thenReturn(Optional.of(associado));

        when(featureToogleConfig.getCheckingCpf()).thenReturn(false);
        var voto = new VotoDTO(
                sessaoVotacaoId,
                associadoId,
                VotoEnum.NAO
        );

        var votoEntity = new Voto();
        votoEntity.setAssociado(associado);
        votoEntity.setSessaoVotacao(sessaoVotacaoMock);
        sessaoVotacaoMock.setVoto(votoEntity);

        registrarVotos.execute(voto);

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


        var voto = new VotoDTO(
                sessaoVotacaoId,
                associado.getId(),
                VotoEnum.NAO
        );

        var votoEntity = new Voto();
        votoEntity.setId(null);
        votoEntity.setAssociado(associado);
        votoEntity.setSessaoVotacao(sessaoVotacaoMock);
        when(sessaoRepository.findById(sessaoVotacaoId)).thenReturn(Optional.of(sessaoVotacaoMock));

        when(votoRepository.existsBySessaoVotacaoIdAndAssociadoId(sessaoVotacaoId, associadoId)).thenReturn(true);

        when(associadoRepository.findById(associadoId)).thenReturn(Optional.of(associado));

        when(checkingCPF.checkCpf("39219796848")).thenReturn(new StatusCpfDTO("ABLE_TO_VOTE"));

        assertThrows(IllegalStateException.class, () -> {
            registrarVotos.execute(voto);
        });
    }
}
