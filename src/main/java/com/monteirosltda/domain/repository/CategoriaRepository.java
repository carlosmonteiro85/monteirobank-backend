package com.monteirosltda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monteirosltda.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
}
