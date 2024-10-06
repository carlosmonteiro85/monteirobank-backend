package com.monteirosltda.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monteirosltda.domain.model.Categoria;
import com.monteirosltda.domain.model.enuns.TipoCategoriaEnum;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    @Query("SELECT c FROM Categoria c WHERE c.tipoCategoria = :tipoCategoria")
    List<Categoria> findCategoriasByTipoCategoria(@Param("tipoCategoria") TipoCategoriaEnum tipoCategoria);
}
