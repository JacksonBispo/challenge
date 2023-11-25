package com.challeng.repository;

import com.challeng.domain.Associado;
import com.challeng.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado,Long > {
}

