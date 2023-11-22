package com.challeng.repository;

import com.challeng.domain.Sessao;
import com.challeng.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto,Long > {
    boolean existsBySessaoVotacaoIdAndAssociadoId(Long sessaoVotacaoId, Long associadoId);
}

