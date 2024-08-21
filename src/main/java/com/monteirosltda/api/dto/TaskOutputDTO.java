package com.monteirosltda.api.dto;

import java.math.BigDecimal;

import com.monteirosltda.domain.model.enuns.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskOutputDTO {
    private Long id;
    private String descricao;
    private StatusEnum status;
    private BigDecimal valor;
}
