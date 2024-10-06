package com.monteirosltda.api.dto;

import lombok.Data;

@Data
public class CategoriaDto {
    private Long id;
    private String descricao;
    private Integer tipoCategoria;
}
