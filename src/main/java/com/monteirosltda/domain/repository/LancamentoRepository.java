package com.monteirosltda.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monteirosltda.domain.model.Lancamento;
import com.monteirosltda.domain.model.enuns.TipoCategoriaEnum;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

    @Query("SELECT l FROM Lancamento l JOIN Categoria c ON c.id = l.categoria  WHERE c.tipoCategoria = :tipoCategoria")
    List<Lancamento> findCategoriasByTipoCategoria(@Param("tipoCategoria") TipoCategoriaEnum tipoCategoria);

    @Query("SELECT SUM(l.valor) FROM Lancamento l WHERE MONTH(l.dataCadastro) = :mes AND YEAR(l.dataCadastro) = :ano AND l.categoria.tipoCategoria = :tipoCategoria")
    BigDecimal somarValoresPorMesEAno(@Param("mes") int mes, @Param("ano") int ano, @Param("tipoCategoria") TipoCategoriaEnum tipoCategoria);
}
