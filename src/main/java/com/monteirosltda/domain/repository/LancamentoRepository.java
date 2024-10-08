package com.monteirosltda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monteirosltda.domain.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
}
