package com.monteirosltda.api.dto;

import java.math.BigDecimal;

import com.monteirosltda.domain.model.Conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaOutputDTO {
    private Long id;
    private BigDecimal saldoTotal;
    private BigDecimal saldoLiberado;
    private BigDecimal saldoBloqueado;
    private UserDTO user;

    public static ContaOutputDTO toDto(Conta conta){
        return ContaOutputDTO.builder()
            .id(conta.getId())
            .saldoTotal(conta.getSaldoBloqueado().add(conta.getSaldoLiberado()))
            .saldoBloqueado(conta.getSaldoBloqueado())
            .saldoLiberado(conta.getSaldoLiberado())
            .user(UserDTO.toDto(conta.getUser()))
        .build();
    }
}
