package com.monteirosltda.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monteirosltda.domain.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

    @Query(value = "SELECT c FROM Conta c Join User u ON c.id = u.conta.id where u.email = :titular")
    Optional<Conta> findByContaForTitular(@Param("titular") String titular);
}
