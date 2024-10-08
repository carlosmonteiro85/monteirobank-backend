package com.monteirosltda.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class LancamentoDTO {
    private Long id;
    private LocalDate dataCadastro;
    private Long idCategoria;
    private String descricao;
    private BigDecimal valor;
    private LocalDate pagoEm;
    private Integer codStatus;
}
