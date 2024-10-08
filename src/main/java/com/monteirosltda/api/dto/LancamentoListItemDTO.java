package com.monteirosltda.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class LancamentoListItemDTO {
    private Long id;
    private LocalDate dataCadastro;
    private String categoria;
    private String descricao;
    private String valor;
    private BigDecimal impactoReceita;
    private LocalDate pagoEm;
    private LocalDate dataVencimento;
    private Integer codStatus;
}
