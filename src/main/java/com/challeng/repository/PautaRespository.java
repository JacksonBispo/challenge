package com.challeng.repository;

import com.challeng.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRespository extends JpaRepository<Pauta,Long > {
}
