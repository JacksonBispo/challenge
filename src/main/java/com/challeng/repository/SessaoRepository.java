package com.challeng.repository;

import com.challeng.domain.Pauta;
import com.challeng.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao,Long > {
}

