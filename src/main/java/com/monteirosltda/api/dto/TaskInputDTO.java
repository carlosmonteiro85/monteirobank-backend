package com.monteirosltda.api.dto;

import java.math.BigDecimal;

import com.monteirosltda.domain.model.Task;

import lombok.Data;

@Data
public class TaskInputDTO {
    private String descricao;
    private BigDecimal valor;


    public Task toEntity(){
        return Task.builder()
            .descricao(this.descricao)
            .valor(this.valor)
        .build();
    }
}
