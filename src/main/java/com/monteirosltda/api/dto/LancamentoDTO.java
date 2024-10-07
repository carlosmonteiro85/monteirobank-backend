package com.monteirosltda.api.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LancamentoDTO {
    private Long id;
    private String dataCadastro;
    private Long idCategoria;
    private String descricao;
    private BigDecimal valor;
    private String pagoEm;
    private Integer codStatus;
}
