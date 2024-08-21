package com.monteirosltda.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conta")
public class Conta {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(name = "saldo_liberado")
    private BigDecimal saldoLiberado = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "saldo_bloqueado")
    private BigDecimal saldoBloqueado = BigDecimal.ZERO;

    @OneToOne(mappedBy = "conta", fetch = FetchType.EAGER)
    private User user;
}
