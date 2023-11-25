package com.challeng.useCases;

import com.challeng.configuration.swagger.FeatureToogleConfig;
import com.challeng.domain.Associado;
import com.challeng.dto.AssociadoDTO;
import com.challeng.exception.CpfinvalidException;
import com.challeng.repository.AssociadoRepository;
import com.challeng.service.CheckingCPF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAssociado {

    private final AssociadoRepository associadoRepository;

    private final CheckingCPF checkingCPF;

    private final FeatureToogleConfig featureToogleConfig;



    public AssociadoDTO execute(AssociadoDTO associadoDTO) {
        if(featureToogleConfig.getCheckingCpf()) {
            var status = checkingCPF.checkCpf(associadoDTO.cpf());
            if ("UNABLE_TO_VOTE".equals(status.status())) {
                throw new CpfinvalidException("cpf nao valido");
            }
        }
        var associado = new Associado();
        associado.setName(associadoDTO.name());
        associado.setCpf(associadoDTO.cpf());

        var associadoSaved = associadoRepository.save(associado);
        return new AssociadoDTO(associadoSaved.getId(), associadoSaved.getName(), associadoSaved.getCpf());
    }

}
